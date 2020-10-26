package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_ticket")
@EqualsAndHashCode(callSuper = false)
public class Ticket extends BaseEntity {
    private String ticketNumber;

    private Boolean isCheckIn = Boolean.FALSE;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "route_information_id")
    private RouteInformation routeInformation;
}
