package com.alasdeplata.services;

import com.alasdeplata.dto.chat.FlightChatbotResponse;

public interface FlightMemoryService {
    void saveFlightForUser(String username, FlightChatbotResponse flight);
    FlightChatbotResponse getFlightForUser(String username);
    void clearFlightForUser(String username);
}
