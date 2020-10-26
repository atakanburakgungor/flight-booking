package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_card_info")
public class CardInfo extends BaseEntity {

    // ozel erisim yetkisi eklenebilir sifreli sekilde kaydedilmeli / user id ayri olmali

    private UUID passengerId;

    private String cardType;

    @Column
    @ColumnTransformer(read = "pgp_sym_decrypt(cardHolderName, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String cardHolderName;

    @Column
    @ColumnTransformer(read = "pgp_sym_decrypt(cardNumber, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String cardNumber;

    private String expiredDate;

    @Column
    @ColumnTransformer(read = "pgp_sym_decrypt(cvvCode, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String cvvCode;

}
