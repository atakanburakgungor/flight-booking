package com.burakgungor.airlinebooking.model;

import com.burakgungor.airlinebooking.entity.PassengerIdentification;
import com.burakgungor.airlinebooking.entity.RouteInformation;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequestResponse {
    private UUID orderId;
    private PassengerIdentification passengerIdentification;
    private RouteInformation routeInformation;
}
