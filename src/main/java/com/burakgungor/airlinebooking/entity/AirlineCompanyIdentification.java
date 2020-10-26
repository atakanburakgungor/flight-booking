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
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_airline_company_identification")
public class AirlineCompanyIdentification extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Type type;

    private String identificationId;

    private String issuingAuthority;

    private LocalDateTime issuingDate;

    public enum Type {
        VKN
    }
}
