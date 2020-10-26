package com.burakgungor.airlinebooking.repository;

import com.burakgungor.airlinebooking.entity.AirlineCompany;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirlineCompanyRepository extends CrudRepository<AirlineCompany, UUID>, JpaSpecificationExecutor<AirlineCompany> {
}
