package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "t_passenger_identification")
@EqualsAndHashCode(callSuper = false)
public class PassengerIdentification extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Type type;

    private String identificationNumber;

    private String issuingAuthority;

    private String nationality;

    private LocalDateTime issuingDate;

    public enum Type {
        NATIONAL_ID,
        PASSPORT
    }
}
