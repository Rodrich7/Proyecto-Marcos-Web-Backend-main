package com.alasdeplata.controllers;

import com.alasdeplata.services.impl.ContactoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alasdeplata.dto.contacto.ContactoRequest;
import com.alasdeplata.dto.contacto.ContactoResponse;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController // Define que esta clase es un controlador REST
@RequestMapping("/api/v1/contacto") // Ruta base del endpoint
@RequiredArgsConstructor // Inyecta automáticamente el ContactoService
public class ContactoController {

    private final ContactoServiceImpl contactoService;

    @PostMapping
    public ResponseEntity<ContactoResponse> crearContacto(
            @RequestBody @Valid ContactoRequest contactoRequest) {

        // Llama al servicio para guardar el contacto
        ContactoResponse respuesta = contactoService.guardarContacto(contactoRequest);

        // Devuelve respuesta con estado 201
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}
