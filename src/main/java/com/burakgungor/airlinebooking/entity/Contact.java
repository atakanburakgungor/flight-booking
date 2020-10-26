package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"type", "medium"}, callSuper = false)
@Table(name = "t_contact")
public class Contact extends BaseEntity {

    private Boolean preferred;

    @Enumerated(EnumType.STRING)
    private Type type;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contact_id")
    private Set<ContactDetails> contactDetails;

    public enum Type {
        EMAIL_ADDRESS, TELEPHONE_NUMBER, POSTAL_ADDRESS, FAX_NUMBER
    }

}
