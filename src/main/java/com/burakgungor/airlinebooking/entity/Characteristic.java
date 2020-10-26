package com.burakgungor.airlinebooking.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = false, of = {"name", "value"})
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "t_characteristic")
public class Characteristic extends BaseEntity {

    @NonNull
    private String name;

    @NonNull
    private String value;

    public static Characteristic of(String name, String value) {
        return new Characteristic(name, value);
    }
}
