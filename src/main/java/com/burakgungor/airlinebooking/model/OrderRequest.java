package com.burakgungor.airlinebooking.model;

import com.burakgungor.airlinebooking.entity.*;
import lombok.Data;

@Data
public class OrderRequest {

    private String orderCode;

    private AirCraft airCraft;

    private Airport airport;

    private PassengerIdentification passengerIdentification;

    //private PassengerRequest passengerRequest;

    private Boolean isPaymentOk = false;

    private RouteInformation routeInformation;

    private Boolean reserved;

    private Boolean isTicketCreated = false;
}
