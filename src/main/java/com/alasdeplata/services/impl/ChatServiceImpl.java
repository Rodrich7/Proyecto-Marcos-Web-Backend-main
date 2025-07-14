package com.alasdeplata.services.impl;

import com.alasdeplata.dto.chat.ChatResponse;
import com.alasdeplata.services.ChatService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final WebClient webClient;

    public ChatServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ChatResponse processUserMessage(String userMessage) {
        System.out.println("💬 Recibido del usuario: " + userMessage);

        try {
            String prompt = generatePrompt(userMessage); // ⚠️ Aquí podría fallar
            System.out.println("🛠 Prompt generado:\n" + prompt);

            Map<String, Object> requestBody = Map.of(
                    "model", "gemma:2b",
                    "prompt", prompt,
                    "stream", false
            );

            System.out.println("📦 Enviando a Ollama: " + requestBody); // ← ESTO TAMBIÉN

            Map<?, ?> response = webClient
                    .post()
                    .uri("/api/generate")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            String reply = Optional.ofNullable(response)
                    .map(r -> r.get("response"))
                    .map(Object::toString)
                    .orElse("Lo siento 😔, no se recibió respuesta de la IA.");

            return new ChatResponse(postProcessResponse(reply));

        } catch (WebClientResponseException e) {
            System.out.println("❌ Error HTTP al contactar a Ollama: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return new ChatResponse("Lo siento 😔, hubo un error del servidor: " + e.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace(); // <--- para ver el error completo
            return new ChatResponse("Lo siento 😔, ocurrió un error inesperado al procesar tu mensaje.");
        }
    }

    private String generatePrompt(String userMessage) {
        return """
            Eres un asistente virtual amigable y profesional llamado AlasBot, especializado en reservas de vuelos.
            El usuario te dice: "%s"
            Responde como si fueras parte de la agencia Alas de Plata.
            Sé amable, breve y ofrece ayuda clara. Si puedes recomendar algo de los destinos, hazlo.
            """.formatted(userMessage).strip();
    }

    private String postProcessResponse(String reply) {
        if (reply.toLowerCase().contains("hola")) {
            return "¡Hola! 😊 Soy AlasBot, tu asistente virtual de viajes. ¿Cómo puedo ayudarte hoy?";
        }
        return reply + "\n\n✈️ *Con cariño, tu asistente AlasBot*";
    }
}
