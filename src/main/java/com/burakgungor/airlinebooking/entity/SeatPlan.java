package com.burakgungor.airlinebooking.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_seat_plan")
@EqualsAndHashCode(callSuper = false)
public class SeatPlan extends BaseEntity {

    private Integer number;

    private char code;

    private boolean reservedStatus = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_information_id")
    private RouteInformation routeInformation;

    @Embedded
    private AirFare airFare;
}
