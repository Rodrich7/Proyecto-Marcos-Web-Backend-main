package com.alasdeplata.dto.chat;

public record FlightChatbotResponse(
        Long id,
        String destino,
        String fecha,
        String horaSalida,
        String precio,
        String aerolinea
) {}

