package com.alasdeplata.services.impl;

import com.alasdeplata.dto.chat.FlightChatbotResponse;
import com.alasdeplata.services.FlightMemoryService;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FlightMemoryServiceImpl implements FlightMemoryService{

    private final ConcurrentHashMap<String, FlightChatbotResponse> memory = new ConcurrentHashMap<>();

    @Override
    public void saveFlightForUser(String username, FlightChatbotResponse vuelo) {
        memory.put(username, vuelo);
    }

    @Override
    public FlightChatbotResponse getFlightForUser(String username) {
        return memory.get(username);
    }

    @Override
    public void clearFlightForUser(String username) {
        memory.remove(username);
    }

}
