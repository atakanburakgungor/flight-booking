package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_air_craft")
public class AirCraft extends BaseEntity {

    private String manufacturer;

    private String countryOfOrigin;

    private String model;

    private String type;

    private String powerplants;

    private String dimensions;

    private Integer capacity;

    private String weights;

    private String performance;

    private String details;

}
