package com.burakgungor.airlinebooking.repository;

import com.burakgungor.airlinebooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, UUID>, JpaSpecificationExecutor<Ticket> {
}
