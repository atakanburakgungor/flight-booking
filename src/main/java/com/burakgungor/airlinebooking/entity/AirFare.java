package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
@Data
public class AirFare{

    private Integer amount;

    private Integer fare;

    private Integer netFare;

    private Integer discount;

    private FareClass fareClass;

    private String description;

    public enum FareClass {
        FIRST_CLASS, BUSINESS_CLASS, ECONOMY, PREMIUM_ECONOMY
    }

}
