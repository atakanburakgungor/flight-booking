package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.AirlineCompany;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/rest/airline-company")
public interface AirlineCompanyController {
    @ApiOperation(value = "Create a new AirlineCompany", nickname = "createAirlineCompany", response = AirlineCompany.class, tags = {"airlineCompany"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AirlineCompany.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/create",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<AirlineCompany> createAirlineCompany(@ApiParam(value = "AirlineCompany resource body", required = true) @RequestBody AirlineCompany airlineCompany
    );

    @ApiOperation(value = "Find AirlineCompany by ID", nickname = "findAirlineCompany", notes = "Returns a single AirlineCompany", response = AirlineCompany.class, tags = {"AirlineCompany"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AirlineCompany.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{airlineCompanyId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<AirlineCompany> findAirlineCompanyById(@ApiParam(value = "ID of the AirlineCompany resource to return", required = true) @PathVariable("airlineCompanyId") UUID airlineCompanyId);

    @ApiOperation(value = "Delete AirlineCompany", nickname = "deleteAirlineCompany", notes = "Returns a deleted AirlineCompany information", tags = {"AirlineCompany"})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{airlineCompanyId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<AirlineCompany> deleteAirlineCompany(@ApiParam(value = "ID of the AirlineCompany resource to return", required = true) @PathVariable("airlineCompanyId") UUID airlineCompanyId);

    @ApiOperation(value = "Find all AirlineCompanies", nickname = "findAllAirlineCompany", notes = "Returns a list of AirlineCompany", response = List.class, tags = {"AirlineCompany"})
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
    ResponseEntity<List<AirlineCompany>> findAllAirlineCompanies();

    @ApiOperation(value = "Find all airline companies by searchCriteria", nickname = "findAllAirlineCompanyBySearchCriteria", notes = "Returns a list of airline company", response = List.class, tags = {"airlineCompanies"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/find-all-by-search-criteria",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<AirlineCompany>> search(@ApiParam(value = "Search criteria", required = true) @RequestParam String search);

}
