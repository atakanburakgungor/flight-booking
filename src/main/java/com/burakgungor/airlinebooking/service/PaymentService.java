package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.Ticket;
import com.burakgungor.airlinebooking.model.ValidatePayment;
import com.burakgungor.airlinebooking.model.TransactionRequest;
import com.burakgungor.airlinebooking.model.TransactionResponse;
import com.burakgungor.airlinebooking.repository.CardInfoRepository;
import com.burakgungor.airlinebooking.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PaymentService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CardInfoRepository cardInfoRepository;

    public TransactionResponse createPayment(TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = new TransactionResponse();
        ValidatePayment validatePayment = CommonUtils.validateAndMaskCreditCard(transactionRequest.getCardInfo().getCardNumber());
        if (validatePayment.getIsValidated().equals(Boolean.FALSE)) {
            transactionRequest.setIsPaymentOk(Boolean.FALSE);
            transactionResponse.setIsTransactionOk(Boolean.FALSE);
            transactionResponse.setTicket(null);
            transactionResponse.setMessage("Card info is not valid !");
            return transactionResponse;
        }
        cardInfoRepository.save(transactionRequest.getCardInfo());
        Boolean isPaymentSuccess = sendPaymentService(transactionRequest);
        if (isPaymentSuccess) {
            transactionRequest.setIsPaymentOk(Boolean.TRUE);
            return createPaymentTransaction(transactionRequest);
        } else {
            transactionRequest.setIsPaymentOk(Boolean.FALSE);
            transactionResponse.setIsTransactionOk(Boolean.FALSE);
            transactionResponse.setTicket(null);
            transactionResponse.setMessage("Payment is failed !");
            return transactionResponse;
        }
    }

    private Boolean sendPaymentService(TransactionRequest transactionRequest) {
        // Dis entegrasyona git odeme onayi al
        return Boolean.TRUE;
    }

    public TransactionResponse createPaymentTransaction(TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = new TransactionResponse();
        if (transactionRequest.getIsPaymentOk().equals(Boolean.TRUE)) {
            orderService.updateOrderPaymentStatus(transactionRequest.getOrderId());
            transactionResponse.setIsTransactionOk(Boolean.TRUE);
            Ticket ticket = ticketService.createTicket(transactionRequest);
            transactionResponse.setTicket(ticket);
        } else {
            transactionResponse.setIsTransactionOk(Boolean.FALSE);
            transactionResponse.setTicket(null);
        }
        return transactionResponse;
    }

}
