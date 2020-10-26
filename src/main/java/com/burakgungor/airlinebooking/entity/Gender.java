package com.burakgungor.airlinebooking.entity;

import lombok.Getter;

import java.security.InvalidParameterException;

@Getter
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");

    private String value;

    Gender(String value) {
        this.value = value;
    }
    public static Gender fromValue(String value) {
        for (Gender eventType : Gender.values()) {
            if (eventType.getValue().equals(value)) {
                return eventType;
            }
        }

        throw new InvalidParameterException("There is no gender with value: " + value);
    }
}
