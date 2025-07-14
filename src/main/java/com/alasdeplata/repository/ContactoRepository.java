package com.alasdeplata.repository;

import com.alasdeplata.models.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Este repositorio permite realizar operaciones CRUD sobre la entidad Contact
 * gracias a JpaRepository.
 * No es necesario escribir ninguna implementación. Spring Data JPA se encarga.
 */
@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    // Puedes agregar métodos personalizados aquí si los necesitas.
}

