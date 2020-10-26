package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_airport")
public class Airport extends BaseEntity {

    private String ident;

    private Type type;

    private Long latitude;

    private Long longitude;

    private String elevation;

    private String continent;

    private String isoCountry;

    private String isoRegion;

    private String municipality;

    private Boolean scheduledService;

    private String iataCode;

    private String gpsCode;

    public enum Type {
        SMALL, MEDIUM, LARGE, HELIPORT, CLOSED, SEAPLANE, BALLOONPORT
    }
}
