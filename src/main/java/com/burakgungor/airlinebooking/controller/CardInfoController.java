package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.CardInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@RequestMapping("/rest/cardinfo")
public interface CardInfoController {

    @ApiOperation(value = "Create a new CardInfo", nickname = "createCardInfo", response = CardInfo.class, tags = {"CardInfo"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CardInfo.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/create",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<CardInfo> createCardInfo(@ApiParam(value = "CardInfo resource body", required = true) @RequestBody CardInfo CardInfo
    ) throws Exception;

    @ApiOperation(value = "Find CardInfo by ID", nickname = "findCardInfo", notes = "Returns a single CardInfo", response = CardInfo.class, tags = {"CardInfo"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CardInfo.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{cardInfoId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<CardInfo> findCardInfoById(@ApiParam(value = "ID of the CardInfo resource to return", required = true) @PathVariable("cardInfoId") UUID cardInfoId) throws Exception;

    @ApiOperation(value = "Delete CardInfo", nickname = "deleteCardInfo", notes = "Returns a deleted CardInfo information", response = CardInfo.class, tags = {"CardInfo"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CardInfo.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/{cardInfoId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<CardInfo> deleteCardInfo(@ApiParam(value = "ID of the CardInfo resource to return", required = true) @PathVariable("cardInfoId") UUID cardInfoId) throws Exception;

}
