package com.burakgungor.airlinebooking.repository;

import com.burakgungor.airlinebooking.entity.RouteInformation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RouteInformationRepository extends CrudRepository<RouteInformation, UUID>, JpaSpecificationExecutor<RouteInformation> {
}
