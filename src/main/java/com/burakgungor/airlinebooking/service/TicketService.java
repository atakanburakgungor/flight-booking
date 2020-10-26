package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.Route;
import com.burakgungor.airlinebooking.entity.Ticket;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.model.TransactionRequest;
import com.burakgungor.airlinebooking.repository.TicketRepository;
import com.burakgungor.airlinebooking.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TicketService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteInformationService routeInformationService;

    private static final String TICKET_PREFIX = "TCK-";

    public Ticket createTicket(TransactionRequest transactionRequest) {
        //check order is exist
        orderService.getOrder(transactionRequest.getOrderId());
        Route route = routeService.findRouteById(transactionRequest.getRouteInformation().getRoutes().getId());
        Ticket ticket = new Ticket();
        ticket.setIsCheckIn(Boolean.FALSE);
        ticket.setRoute(route);
        ticket.setTicketNumber(CommonUtils.generateCode(TICKET_PREFIX));
        return ticketRepository.save(ticket);
    }


    public Ticket findTicketById(UUID ticketId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if (!ticketOptional.isPresent()) {
            throw new AppException("Ticket not found for this request : " + ticketId);
        }
        return ticketOptional.get();
    }

    public Ticket deleteTicket(UUID ticketId) {
        Ticket ticket = findTicketById(ticketId);
        ticket.setEndDate(LocalDateTime.now());
        ticketRepository.save(ticket);
        orderService.unlockSeatPlanForSell(ticket.getRouteInformation().getSeatPlan().get(0));
        routeInformationService.decreaseCapasity(ticket.getRouteInformation());
        return ticket;
    }

    public List<Ticket> findAllTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketRepository.findAll().forEach(ticketList::add);
        return ticketList;
    }

    public Ticket checkInTicket(UUID ticketId) {
        Ticket ticket = findTicketById(ticketId);
        ticket.setIsCheckIn(Boolean.TRUE);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> search(Specification<Ticket> spec) {
        return ticketRepository.findAll(spec);
    }
}
