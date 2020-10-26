package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_contact_details")
public class ContactDetails extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Type type;

    private String emailAddress;

    private String faxNumber;

    private String phoneNumber;

    @Embedded
    private DetailedAddress detailedAddress;

    public enum Type {
        WORK, HOME, MOBILE
    }
}
