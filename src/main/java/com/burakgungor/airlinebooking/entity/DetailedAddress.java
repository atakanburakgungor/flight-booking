package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Embeddable
public class DetailedAddress {

    //Posta Kodu
    private String postCode;

    // Eyalet
    private String stateOrProvince;

    private String country;

    // İl
    private String city;

    // İlce
    private String county;

    // Semt
    private String locality;

    // Mahalle
    private String neighborhood;

    // Cadde
    private String street1;

    // Sokak
    private String street2;

    // Sokak
    private String street2Id;

    // Bina No
    private String buildingNumber;

    // Kapı no
    private String doorNumber;

    // bbk no
    private String bbkNumber;

    // Adres no
    private String addressNumber;

    // Full Adres
    private String formattedAddress;
}
