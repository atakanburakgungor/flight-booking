package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.Ticket;
import com.burakgungor.airlinebooking.model.TransactionRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/rest/ticket")
public interface TicketController {
    @ApiOperation(value = "Create a new Ticket", nickname = "createTicket", response = Ticket.class, tags = {"Ticket"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Ticket.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/create",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Ticket> createTicket(@ApiParam(value = "Ticket resource body", required = true) @RequestBody TransactionRequest transactionRequest
    );

    @ApiOperation(value = "Find Ticket by ID", nickname = "findTicket", notes = "Returns a single Ticket", response = Ticket.class, tags = {"Ticket"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Ticket.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{ticketId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Ticket> findTicketById(@ApiParam(value = "ID of the Ticket resource to return", required = true) @PathVariable("ticketId") UUID ticketId);

    @ApiOperation(value = "Delete Ticket", nickname = "deleteTicket", notes = "Returns a deleted Ticket information", tags = {"Ticket"})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{ticketId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Ticket> deleteTicket(@ApiParam(value = "ID of the Ticket resource to return", required = true) @PathVariable("ticketId") UUID ticketId);

    @ApiOperation(value = "Find all Tickets", nickname = "findAllTicket", notes = "Returns a list of Ticket", response = List.class, tags = {"Ticket"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/find-all",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Ticket>> findAllTickets();


    @ApiOperation(value = "Check In Ticket", nickname = "checkInTicket", notes = "Returns a list of Ticket", response = Ticket.class, tags = {"Ticket"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Ticket.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/checkInTicket",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Ticket> checkInTicket(@ApiParam(value = "ID of the Ticket resource to return", required = true) @PathVariable("ticketId") UUID ticketId);


    @ApiOperation(value = "Find all tickets by searchCriteria", nickname = "findAllTicketsBySearchCriteria", notes = "Returns a list of ticket", response = List.class, tags = {"tickets"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/find-all-ticket-by-search-criteria",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Ticket>> search(@ApiParam(value = "Search criteria", required = true) @RequestParam String search);
}
