package com.burakgungor.airlinebooking.model;

import com.burakgungor.airlinebooking.entity.CardInfo;
import com.burakgungor.airlinebooking.entity.PassengerIdentification;
import com.burakgungor.airlinebooking.entity.RouteInformation;
import lombok.Data;

import java.util.UUID;

@Data
public class TransactionRequest {
    private UUID orderId;
    private PassengerIdentification passengerIdentification;
    private RouteInformation routeInformation;
    private Boolean isPaymentOk;
    private CardInfo cardInfo;
}
