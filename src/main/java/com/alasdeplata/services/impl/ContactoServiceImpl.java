package com.alasdeplata.services.impl;

import org.springframework.stereotype.Service;

import com.alasdeplata.dto.contacto.ContactoRequest;
import com.alasdeplata.dto.contacto.ContactoResponse;
import com.alasdeplata.models.Contacto;
import com.alasdeplata.repository.ContactoRepository;
import com.alasdeplata.services.ContactoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactoServiceImpl implements ContactoService {

    private final ContactoRepository contactoRepository;

    @Override
    public ContactoResponse guardarContacto(ContactoRequest request) {
        Contacto contacto = new Contacto();
        contacto.setNombre(request.getNombre());
        contacto.setEmail(request.getEmail());
        contacto.setMensaje(request.getMensaje());
        contactoRepository.save(contacto);

        return new ContactoResponse("Contacto guardado correctamente", true);
    }
}
