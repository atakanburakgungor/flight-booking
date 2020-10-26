package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.Ticket;
import com.burakgungor.airlinebooking.model.TransactionRequest;
import com.burakgungor.airlinebooking.search.CustomRsqlVisitor;
import com.burakgungor.airlinebooking.service.TicketService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@CrossOrigin
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TicketControllerImpl implements TicketController {

    @NonNull
    private TicketService ticketService;

    @Override
    public ResponseEntity<Ticket> createTicket(TransactionRequest transactionRequest) {
        Ticket createdTicket = ticketService.createTicket(transactionRequest);
        return new ResponseEntity<>(createdTicket, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ticket> findTicketById(UUID ticketId) {
        Ticket createdTicket = ticketService.findTicketById(ticketId);
        return new ResponseEntity<>(createdTicket, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ticket> deleteTicket(UUID ticketId) {
        Ticket Ticket = ticketService.deleteTicket(ticketId);
        return new ResponseEntity<>(Ticket, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ticket>> findAllTickets() {
        List<Ticket> airlineCompanies = ticketService.findAllTickets();
        return new ResponseEntity<>(airlineCompanies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ticket> checkInTicket(UUID ticketId) {
        Ticket ticket = ticketService.checkInTicket(ticketId);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ticket>> search(String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Ticket> spec = rootNode.accept(new CustomRsqlVisitor<>());
        List<Ticket> searches = ticketService.search(spec);
        return new ResponseEntity<>(searches, HttpStatus.OK);
    }
}
