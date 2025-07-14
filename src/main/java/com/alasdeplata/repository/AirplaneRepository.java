package com.alasdeplata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.models.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

}
