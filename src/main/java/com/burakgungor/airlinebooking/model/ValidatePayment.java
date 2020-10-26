package com.burakgungor.airlinebooking.model;

import lombok.Data;

@Data
public class ValidatePayment {
    private Boolean isValidated;
    private String creditCardNumber;
    private String maskedCardNumber;
}
