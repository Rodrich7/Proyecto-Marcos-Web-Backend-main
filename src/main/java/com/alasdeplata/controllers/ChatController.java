package com.alasdeplata.controllers;

import com.alasdeplata.dto.chat.ChatRequest;
import com.alasdeplata.dto.chat.ChatResponse;
import com.alasdeplata.services.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody @Valid ChatRequest request) {
        System.out.println("🎯 Mensaje recibido en el controlador: " + request.message());
        ChatResponse response = chatService.processUserMessage(request.message());
        System.out.println("🤖 Respuesta generada por el servicio: " + response.reply());
        return ResponseEntity.ok(response);
    }
}
