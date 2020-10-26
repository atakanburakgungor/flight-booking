package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.Airport;
import com.burakgungor.airlinebooking.entity.Ticket;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequestMapping("/rest/airport")
public interface AirportController {

    @ApiOperation(value = "Create a new Airport", nickname = "createAirport", response = Airport.class, tags = {"airport"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Airport.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/create-Airport",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Airport> createAirport(@ApiParam(value = "Airport resource body", required = true) @RequestBody Airport Airport
    );

    @ApiOperation(value = "Find Airport by ID", nickname = "findAirport", notes = "Returns a single Airport", response = Airport.class, tags = {"airport"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Airport.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{airportId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Airport> findAirportById(@ApiParam(value = "ID of the Airport resource to return", required = true) @PathVariable("airportId") UUID airportId);

    @ApiOperation(value = "Delete Airport", nickname = "deleteAirport", notes = "Returns a deleted Airport information", response = Airport.class, tags = {"airport"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Airport.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{airportId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Airport> deleteAirport(@ApiParam(value = "ID of the Airport resource to return", required = true) @PathVariable("airportId") UUID airportId);

    @ApiOperation(value = "Find all Airports", nickname = "findAllAirport", notes = "Returns a collections of Airport", response = List.class, tags = {"airport"})
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
    ResponseEntity<List<Airport>> findAllAirports();

    @ApiOperation(value = "Find all airports by searchCriteria", nickname = "findAllAirportsBySearchCriteria", notes = "Returns a list of airport", response = List.class, tags = {"airport"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/find-all-airport-by-search-criteria",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Airport>> search(@ApiParam(value = "Search criteria", required = true) @RequestParam String search);

}
