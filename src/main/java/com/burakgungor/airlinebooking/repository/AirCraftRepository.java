package com.burakgungor.airlinebooking.repository;

import com.burakgungor.airlinebooking.entity.AirCraft;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirCraftRepository extends CrudRepository<AirCraft, UUID>, JpaSpecificationExecutor<AirCraft> {
}
