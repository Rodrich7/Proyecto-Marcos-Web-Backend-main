package com.alasdeplata.services;
import com.alasdeplata.dto.contacto.ContactoRequest;
import com.alasdeplata.dto.contacto.ContactoResponse;

public interface ContactoService {
    ContactoResponse guardarContacto(ContactoRequest request);
}
