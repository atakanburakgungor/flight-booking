package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_order")
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntity {

    private String orderCode;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private AirCraft airCraft;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Airport airport;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private PassengerIdentification passengerIdentification;

    //private PassengerRequest passengerRequest;

    private Boolean isPaymentOk = false;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private RouteInformation routeInformation;

    private Boolean reserved;

    private Boolean isTicketCreated = false;

}
