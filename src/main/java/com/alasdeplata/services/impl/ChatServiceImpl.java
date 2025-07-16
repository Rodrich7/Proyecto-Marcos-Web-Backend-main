package com.alasdeplata.services.impl;

import com.alasdeplata.dto.chat.ChatResponse;
import com.alasdeplata.dto.chat.FlightChatbotResponse;
import com.alasdeplata.dto.reservation.ReservationRequest;
import com.alasdeplata.enums.ReservationStatus;
import com.alasdeplata.services.ChatService;
import com.alasdeplata.services.FlightMemoryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatServiceImpl implements ChatService {

    private final WebClient ollamaClient;
    private final WebClient backendClient;
    private final FlightMemoryService flightMemoryService;
    private final Map<String, String> estadoUsuario = new ConcurrentHashMap<>();

    public ChatServiceImpl(
            @Qualifier("ollamaClient") WebClient ollamaClient,
            @Qualifier("backendClient") WebClient backendClient,
            @Qualifier("flightMemoryServiceImpl") FlightMemoryService flightMemoryService
    ) {
        this.ollamaClient = ollamaClient;
        this.backendClient = backendClient;
        this.flightMemoryService = flightMemoryService;
    }

    private boolean esConfirmacion(String mensaje) {
        String msg = mensaje.toLowerCase().trim();
        return msg.matches(".*\\b(s[ií]|confirmar|dale|quiero reservar|hazlo|ok|va|resérvalo|sí quiero)\\b.*");
    }
    private String postProcessResponse(String reply) {
        if (reply.toLowerCase().contains("hola")) {
            return "¡Hola! 😊 Soy AlasBot, tu asistente virtual de viajes. ¿Cómo puedo ayudarte hoy?";
        }
        return reply + "\n\n✈️ *Con cariño, tu asistente AlasBot*";
    }

    @Override
    public ChatResponse processUserMessage(String userMessage) {

        System.out.println("💬 Recibido del usuario: " + userMessage);

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            if (esConfirmacion(userMessage)) {
                if (!"esperando_confirmacion".equals(estadoUsuario.get(username))) {
                    return new ChatResponse("No hay vuelo pendiente de confirmación 😕. Por favor, solicita uno primero.");
                }

                FlightChatbotResponse vuelo = flightMemoryService.getFlightForUser(username);
                if (vuelo == null) {
                    return new ChatResponse("No hay un vuelo guardado para confirmar 😕. Por favor, pide uno primero.");
                }

                ReservationRequest reservaRequest = new ReservationRequest(1L);


                backendClient.post()
                        .uri("/api/v1/reservas")
                        .bodyValue(reservaRequest)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();

                flightMemoryService.clearFlightForUser(username);
                estadoUsuario.remove(username);

                return new ChatResponse("✅ ¡Reserva realizada exitosamente! Te enviaremos los detalles a tu correo 😄");
            }

            String prompt = generatePrompt(userMessage);
            System.out.println("🛠 Prompt generado:\n" + prompt);

            Map<String, Object> requestBody = Map.of(
                    "model", "llama3",
                    "prompt", prompt,
                    "stream", false
            );

            Map<?, ?> response = ollamaClient
                    .post()
                    .uri("/api/generate")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            String rawReply = Optional.ofNullable(response)
                    .map(r -> r.get("response"))
                    .map(Object::toString)
                    .orElse("Lo siento 😔, no se recibió respuesta de la IA.");

            int start = rawReply.indexOf('{');
            int end = rawReply.lastIndexOf('}');
            if (start == -1 || end == -1 || start > end) {
                throw new IllegalArgumentException("La respuesta de la IA no contiene JSON válido.");
            }

            String jsonClean = rawReply.substring(start, end + 1);
            System.out.println("🧹 JSON limpio: " + jsonClean);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> datos = mapper.readValue(jsonClean, new TypeReference<>() {});

            String destino = datos.get("destino");
            String fecha = datos.get("fecha");

            if (destino == null || fecha == null) {
                return new ChatResponse("😅 No entendí bien tu destino o la fecha. Intenta escribir algo como: *Quiero volar a Cusco el 15 de agosto*.");
            }

            FlightChatbotResponse vuelo = backendClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/v1/flights/chatbot")
                            .queryParam("destino", destino)
                            .queryParam("fecha", fecha)
                            .build())
                    .retrieve()
                    .bodyToMono(FlightChatbotResponse.class)
                    .block();

            if (vuelo == null) {
                return new ChatResponse("Lo siento 😔, no encontré vuelos disponibles para ese destino y fecha.");
            }

            flightMemoryService.saveFlightForUser(username, vuelo);
            estadoUsuario.put(username, "esperando_confirmacion");

            return new ChatResponse(postProcessResponse(respuestaVuelo(vuelo)));

        } catch (WebClientResponseException e) {
            System.out.println("❌ Error HTTP al contactar a Ollama o vuelos: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return new ChatResponse("Lo siento 😔, hubo un error al procesar tu mensaje. Código: " + e.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            return new ChatResponse("Lo siento 😔, ocurrió un error inesperado al procesar tu mensaje.");
        }
    }

    private String generatePrompt(String userMessage) {
        return """
        Eres un asistente virtual de viajes llamado AlasBot. Tu tarea es extraer del mensaje del usuario, si es posible, el *destino* y la *fecha* de un vuelo que desea buscar.

        Responde estrictamente con un JSON del siguiente formato:
        {
          "destino": "nombre del destino (ej. Cusco)",
          "fecha": "YYYY-MM-DD" // Usa 2025 por defecto si no se indica el año
        }

        Si no encuentras alguno de los datos, omítelo en el JSON.

        Mensaje del usuario:
        "%s"
        """.formatted(userMessage).strip();
    }

    private String respuestaVuelo(FlightChatbotResponse vuelo) {
        return """
        🛫 *¡Encontré un vuelo para ti!*  
        📍 Destino: *%s*  
        📅 Fecha: *%s*  
        🕒 Hora de salida: *%s*  
        💸 Precio: *S/%.2f*  
        🛫 Aerolínea: *%s*  

        ¿Te gustaría reservarlo ahora? 😊
        """.formatted(
                vuelo.destino(),
                vuelo.fecha(),
                vuelo.horaSalida(),
                Double.parseDouble(vuelo.precio()),
                vuelo.aerolinea()
        );
    }
}
