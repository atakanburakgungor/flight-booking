package com.burakgungor.airlinebooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Table(name = "t_passenger")
@EqualsAndHashCode(callSuper = false)
public class Passenger extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passenger_id")
    @JsonProperty("characteristic")
    private Set<Characteristic> characteristics;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "t_passenger_contact_details", joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_details_id"))
    @JsonProperty("contactDetails")
    private Set<Contact> contactDetails;

    @Version
    @JsonIgnore
    private Long version;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passenger_id")
    @JsonProperty("externalReferences")
    private Set<ExternalReference> externalReferences;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String placeOfBirth;

    private String countryOfBirth;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private LocalDate birthDate;

    private LocalDateTime deathDate;

    private String title;

    private String givenName;

    private String familyName;

    private String middleName;

    private String fullName;

    private String location;

    private String fatherName;

    private String motherName;

    private String maidenName;

    private Boolean isExisting;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passenger_id")
    @JsonProperty("passengerIdentification")
    private List<PassengerIdentification> passengerIdentifications;

    public enum MaritalStatus {
        SINGLE, MARRIED, WIDOW, DIVORCED
    }

    public enum Status {
        UNAPPROVED, VALIDATED, DEFUNCT
    }
    public enum Type {
        TC,FOREIGNER
    }
}
