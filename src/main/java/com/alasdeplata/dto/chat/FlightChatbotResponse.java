package com.alasdeplata.dto.chat;

public record FlightChatbotResponse(
        String destino,
        String fecha,
        String horaSalida,
        String precio,
        String aerolinea
) {}

