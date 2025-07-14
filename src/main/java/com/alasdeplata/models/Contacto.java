// Contact.java
package com.alasdeplata.models;

import jakarta.persistence.*;  // Anotaciones para mapear con base de datos
import lombok.*;              // Anotaciones de Lombok para evitar boilerplate

// Esta clase representa la tabla "contacts" en la base de datos
@Entity
@Table(name = "contacts")
@Data // Lombok: genera getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder // Lombok: permite crear objetos con el patron builder

public class Contacto {

    @Id // Define la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id;
    private String email;
    private String nombre;  // Nombre del remitente del mensaje
    private String correo;  // Correo de quien envía el mensaje
    private String mensaje; // Contenido del mensaje enviado
}
