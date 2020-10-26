package com.burakgungor.airlinebooking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_airline_company")
public class AirlineCompany extends BaseEntity {

    private Boolean isLegalEntity;

    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

    private LocalDateTime existsDuring;

    private String tradingName;

    //Co, Inc, Ltd, Pty Ltd, Plc, Gmb
    private String nameType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "airline_company_id")
    @JsonProperty("companyIdentification")
    private Set<AirlineCompanyIdentification> identifications;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "airline_company_id")
    private Set<AirCraft> airCrafts;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "airline_company_id")
    private List<Route> routes;


    public enum OrganizationType {
        COMPANY, PARTNER
    }
}
