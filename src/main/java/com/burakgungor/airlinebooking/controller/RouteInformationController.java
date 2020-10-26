package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.RouteInformation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/rest/RouteInformation")
public interface RouteInformationController {

    @ApiOperation(value = "Create a new RouteInformation", nickname = "createRouteInformation", response = RouteInformation.class, tags = {"RouteInformation"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RouteInformation.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/create",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RouteInformation> createRouteInformation(@ApiParam(value = "RouteInformation resource body", required = true) @RequestBody RouteInformation RouteInformation
    );

    @ApiOperation(value = "Find RouteInformation by ID", nickname = "findRouteInformation", notes = "Returns a single RouteInformation", response = RouteInformation.class, tags = {"RouteInformation"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RouteInformation.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{routeInformationId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<RouteInformation> findRouteInformationById(@ApiParam(value = "ID of the RouteInformation resource to return", required = true) @PathVariable("routeInformationId") UUID routeInformationId);

    @ApiOperation(value = "Delete RouteInformation", nickname = "deleteRouteInformation", notes = "Returns a deleted RouteInformation information", response = RouteInformation.class, tags = {"RouteInformation"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RouteInformation.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{routeInformationId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<RouteInformation> deleteRouteInformation(@ApiParam(value = "ID of the RouteInformation resource to return", required = true) @PathVariable("routeInformationId") UUID routeInformationId);

    @ApiOperation(value = "Find all RouteInformations", nickname = "findAllRouteInformation", notes = "Returns a list of RouteInformation", response = List.class, tags = {"RouteInformation"})
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
    ResponseEntity<List<RouteInformation>> findAllRouteInformations();

    @ApiOperation(value = "Find all RouteInformations by searchCriteria", nickname = "findAllRouteInformationsBySearchCriteria", notes = "Returns a list of RouteInformation", response = List.class, tags = {"RouteInformations"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/find-all-route-information-by-search-criteria",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<RouteInformation>> search(@ApiParam(value = "Search criteria", required = true) @RequestParam String search);


}
