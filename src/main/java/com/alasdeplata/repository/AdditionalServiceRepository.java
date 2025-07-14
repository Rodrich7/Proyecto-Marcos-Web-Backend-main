package com.alasdeplata.repository;

import com.alasdeplata.models.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {

}
