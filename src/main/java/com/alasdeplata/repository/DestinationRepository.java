package com.alasdeplata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.enums.Continent;
import com.alasdeplata.models.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByContinent(Continent continent);
}
