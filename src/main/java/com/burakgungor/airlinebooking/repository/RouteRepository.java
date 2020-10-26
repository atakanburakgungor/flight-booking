package com.burakgungor.airlinebooking.repository;

import com.burakgungor.airlinebooking.entity.Route;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RouteRepository extends CrudRepository<Route, UUID>, JpaSpecificationExecutor<Route> {
}
