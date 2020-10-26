package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "t_route_information")
@EqualsAndHashCode(callSuper = false)
public class RouteInformation extends BaseEntity {

    private ZonedDateTime departureTime;

    private ZonedDateTime arrivalTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route routes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "route_information_id")
    private List<SeatPlan> seatPlan;

    private Long capacity;

    private int selledTicketNumber;
}
