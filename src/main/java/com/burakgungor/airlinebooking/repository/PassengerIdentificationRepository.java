package com.burakgungor.airlinebooking.repository;

import com.burakgungor.airlinebooking.entity.PassengerIdentification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PassengerIdentificationRepository extends CrudRepository<PassengerIdentification, UUID>, JpaSpecificationExecutor<PassengerIdentification> {
}
