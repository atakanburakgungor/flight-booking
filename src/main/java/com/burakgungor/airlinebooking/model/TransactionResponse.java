package com.burakgungor.airlinebooking.model;

import com.burakgungor.airlinebooking.entity.Ticket;
import lombok.Data;

@Data
public class TransactionResponse {

    private Boolean isTransactionOk;
    private Ticket ticket;
    private String message;
}
