package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.Route;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/rest/route")
public interface RouteController {

    @ApiOperation(value = "Create a new Route", nickname = "createRoute", response = Route.class, tags = {"Route"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Route.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/create",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Route> createRoute(@ApiParam(value = "Route resource body", required = true) @RequestBody Route Route
    );

    @ApiOperation(value = "Find Route by ID", nickname = "findRoute", notes = "Returns a single Route", response = Route.class, tags = {"Route"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Route.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{routeId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Route> findRouteById(@ApiParam(value = "ID of the Route resource to return", required = true) @PathVariable("routeId") UUID routeId);

    @ApiOperation(value = "Delete Route", nickname = "deleteRoute", notes = "Returns a deleted Route information", response = Route.class, tags = {"Route"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Route.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{routeId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Route> deleteRoute(@ApiParam(value = "ID of the Route resource to return", required = true) @PathVariable("routeId") UUID routeId);

    @ApiOperation(value = "Find all Routes", nickname = "findAllRoute", notes = "Returns a list of Route", response = List.class, tags = {"Route"})
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
    ResponseEntity<List<Route>> findAllRoutes();

    @ApiOperation(value = "Find all routes by searchCriteria", nickname = "findAllRoutesBySearchCriteria", notes = "Returns a list of route", response = List.class, tags = {"routes"})
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
    ResponseEntity<List<Route>> search(@ApiParam(value = "Search criteria", required = true) @RequestParam String search);


}
