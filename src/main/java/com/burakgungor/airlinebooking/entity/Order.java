package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "t_order")
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntity {

    private String orderCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "air_craft_id")
    private AirCraft airCraft;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_identification_id")
    private PassengerIdentification passengerIdentification;

    //private PassengerRequest passengerRequest;

    private Boolean isPaymentOk = false;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private RouteInformation routeInformation;

    private Boolean reserved;

    private Boolean isTicketCreated = false;

}
