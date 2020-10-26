package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.Order;
import com.burakgungor.airlinebooking.model.OrderRequest;
import com.burakgungor.airlinebooking.model.OrderRequestResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@RequestMapping("/rest/orderrequest")
public interface OrderRequestController {

    @ApiOperation(value = "Create a new OrderRequest", nickname = "createOrderRequest", response = OrderRequest.class, tags = {"OrderRequest"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderRequest.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/create",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<OrderRequestResponse> createOrderRequest(@ApiParam(value = "OrderRequest resource body", required = true) @RequestBody OrderRequest OrderRequest
    );

    @ApiOperation(value = "Find OrderRequest by ID", nickname = "findOrderRequest", notes = "Returns a single OrderRequest", response = OrderRequest.class, tags = {"OrderRequest"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderRequest.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{orderRequestId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Order> findOrderRequestById(@ApiParam(value = "ID of the OrderRequest resource to return", required = true) @PathVariable("orderRequestId") UUID orderRequestId);

    @ApiOperation(value = "Delete OrderRequest", nickname = "deleteOrderRequest", notes = "Returns a deleted OrderRequest information", response = OrderRequest.class, tags = {"OrderRequest"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderRequest.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{orderRequestId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Order> deleteOrderRequest(@ApiParam(value = "ID of the OrderRequest resource to return", required = true) @PathVariable("orderRequestId") UUID orderRequestId);

    @ApiOperation(value = "Find all OrderRequests", nickname = "findAllOrderRequest", notes = "Returns a list of OrderRequest", response = List.class, tags = {"orderRequest"})
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
    ResponseEntity<List<Order>> findAllOrderRequests();




}
