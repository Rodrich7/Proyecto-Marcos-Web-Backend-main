package com.alasdeplata.services.impl;

import com.alasdeplata.dto.chat.ChatResponse;
import com.alasdeplata.dto.chat.FlightChatbotResponse;
import com.alasdeplata.services.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.fasterxml.jackson.core.type.TypeReference;


import java.util.Map;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final WebClient ollamaClient;
    private final WebClient backendClient;

    public ChatServiceImpl(
            @Qualifier("ollamaClient") WebClient ollamaClient,
            @Qualifier("backendClient") WebClient backendClient) {
        this.ollamaClient = ollamaClient;
        this.backendClient = backendClient;
    }


    @Override
    public ChatResponse processUserMessage(String userMessage) {
        System.out.println("💬 Recibido del usuario: " + userMessage);

        try {
            String prompt = generatePrompt(userMessage);
            System.out.println("🛠 Prompt generado:\n" + prompt);

            Map<String, Object> requestBody = Map.of(
                    "model", "llama3",
                    "prompt", prompt,
                    "stream", false
            );

            System.out.println("📦 Enviando a Ollama: " + requestBody);

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

            System.out.println("🧠 Respuesta de IA: " + rawReply);

            // Extraer destino y fecha del JSON de la respuesta
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

            System.out.println("📌 Destino: " + destino + ", Fecha: " + fecha);

            // Consulta a tu backend (o usa datos simulados si no tienes endpoint real)
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


            // Construcción del mensaje final
            String respuesta = """
                ✈️ Tenemos un vuelo a %s el %s a las %s
                por S/%.2f con %s.
                
                ¿Deseas reservarlo?
                """.formatted(
                    vuelo.destino(),
                    vuelo.fecha(),
                    vuelo.horaSalida(),
                    Double.parseDouble(vuelo.precio()),
                    vuelo.aerolinea()
            );

            return new ChatResponse(respuesta.trim());

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
        Eres un asistente de viajes. Extrae del siguiente mensaje del usuario dos datos en formato JSON:
        {
        "destino": string,
        "fecha": string (en formato YYYY-MM-DD, si solo hay día y mes, asume 2025)
        }

        Mensaje del usuario:
        "%s"
        """.formatted(userMessage).strip();
    }

    private String postProcessResponse(String reply) {
        if (reply.toLowerCase().contains("hola")) {
            return "¡Hola! 😊 Soy AlasBot, tu asistente virtual de viajes. ¿Cómo puedo ayudarte hoy?";
        }
        return reply + "\n\n✈️ *Con cariño, tu asistente AlasBot*";
    }
}
