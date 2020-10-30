package com.burakgungor.airlinebooking.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_company_id")
    private AirlineCompany airlineCompany;

    private int capacity = 0;

    private int selledTicketNumber = 0;
}
