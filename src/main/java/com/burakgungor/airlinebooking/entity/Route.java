package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "t_route")
@EqualsAndHashCode(callSuper = false)
public class Route extends BaseEntity {

    private String departureIata;
    private String departureIcao;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureTerminal;

    private String arrivalIata;
    private String arrivalIcao;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalTerminal;

    private String airlineIata;
    private String airlineIcao;
    private String codeShares;
    private String regNumber;

}
/*
    {"departureIata":"OTP",
            "departureIcao":"LROP",
            "departureTerminal":null,
            "departureTime":"09:15:00",
            "arrivalIata":"TRN",
            "arrivalIcao":"LIMF",
            "arrivalTerminal":null,
            "arrivalTime":"10:45:00",
            "airlineIata":"0B",
            "airlineIcao":"BMS",
            "flightNumber":"101",
            "codeshares":null,
            "regNumber":"YR-BAP"
            }*/
