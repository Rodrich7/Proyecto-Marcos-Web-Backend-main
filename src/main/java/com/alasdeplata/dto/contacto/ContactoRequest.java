package com.alasdeplata.dto.contacto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// Este DTO representa los datos que el FRONTEND enviará al BACKEND para crear un contacto
@Data
@Getter
@Setter
public class ContactoRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String email;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String mensaje;
}
