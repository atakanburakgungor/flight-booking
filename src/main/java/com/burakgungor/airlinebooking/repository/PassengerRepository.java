package com.burakgungor.airlinebooking.repository;

import com.burakgungor.airlinebooking.entity.Passenger;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, UUID>, JpaSpecificationExecutor<Passenger> {

}
