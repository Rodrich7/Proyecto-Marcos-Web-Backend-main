package com.alasdeplata.services;

import com.alasdeplata.dto.chat.ChatResponse;

public interface ChatService {
    ChatResponse processUserMessage(String userMessage);
}
