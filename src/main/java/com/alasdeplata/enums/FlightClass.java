package com.alasdeplata.enums;

public enum FlightClass {
    ECONOMY,
    BUSINESS,
    FIRST_CLASS;

    public String getDisplayName() {
        return switch (this) {
            case ECONOMY -> "Económica";
            case BUSINESS -> "Ejecutiva";
            case FIRST_CLASS -> "Primera Clase";
        };
    }
}
