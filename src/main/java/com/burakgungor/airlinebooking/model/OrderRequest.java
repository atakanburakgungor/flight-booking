package com.burakgungor.airlinebooking.model;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequest {

    private String orderCode;

    private UUID airCraftId;

    private UUID airportId;

    private UUID passengerIdentificationId;

    //private PassengerRequest passengerRequest;

    private Boolean isPaymentOk = false;

    private UUID seatPlanId;

    private Boolean reserved;

    private Boolean isTicketCreated = false;
}
