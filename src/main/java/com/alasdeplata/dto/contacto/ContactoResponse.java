package com.alasdeplata.dto.contacto;

import lombok.AllArgsConstructor;
import lombok.Data;

// Este DTO representa la respuesta que el BACKEND enviará al FRONTEND luego de guardar un contacto
@Data
@AllArgsConstructor
public class ContactoResponse {
    private String mensaje;
    private boolean creado;
}
