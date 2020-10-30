package com.burakgungor.airlinebooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_card_info")
public class CardInfo extends BaseEntity {

    // ozel erisim yetkisi eklenebilir sifreli sekilde kaydedilmeli / user id ayri olmali

    private UUID passengerId;

    private String cardType;

    private String cardHolderName;

    private String cardNumber;

    private String expiredDate;

    private String cvvCode;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "card_info_id")
    @JsonIgnore
    private Set<Characteristic> characteristics;

}
