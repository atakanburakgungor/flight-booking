package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.CardInfo;
import com.burakgungor.airlinebooking.entity.Order;
import com.burakgungor.airlinebooking.entity.SeatPlan;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.model.OrderRequest;
import com.burakgungor.airlinebooking.model.OrderRequestResponse;
import com.burakgungor.airlinebooking.model.TransactionRequest;
import com.burakgungor.airlinebooking.repository.OrderRepository;
import com.burakgungor.airlinebooking.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SeatPlanService seatPlanService;

    @Autowired
    private RouteInformationService routeInformationService;

    @Autowired
    private PaymentService paymentService;

    private static final String FLI_CODE = "FLI-";

    public OrderRequestResponse createOrderRequest(OrderRequest orderRequest) {
        OrderRequestResponse orderRequestResponse = null;
        lockSeatPlanForSell(orderRequest.getRouteInformation().getSeatPlan().get(0));
        try {
            Order order = mapOrderRequest(orderRequest);
            Order savedOrder = orderRepository.save(order);
            orderRequestResponse = mapOrderRequestResponse(savedOrder);
            createPaymentRequest(orderRequestResponse);
            routeInformationService.incraseCapasity(orderRequest);
        } catch (Exception e) {
            unlockSeatPlanForSell(orderRequest.getRouteInformation().getSeatPlan().get(0));
        }
        return orderRequestResponse;
    }

    public void createPaymentRequest(OrderRequestResponse orderRequestResponse) {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setIsPaymentOk(Boolean.TRUE);
        transactionRequest.setOrderId(orderRequestResponse.getOrderId());
        transactionRequest.setPassengerIdentification(orderRequestResponse.getPassengerIdentification());
        transactionRequest.setRouteInformation(orderRequestResponse.getRouteInformation());
        transactionRequest.setCardInfo(dummyCardInfo(orderRequestResponse.getPassengerIdentification().getId()));
        paymentService.createPayment(transactionRequest);
    }

    private CardInfo dummyCardInfo(UUID id) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardHolderName("Finartz");
        cardInfo.setCardNumber("5170410000000004");
        cardInfo.setCardType("MasterCard");
        cardInfo.setExpiredDate("11/2021");
        cardInfo.setPassengerId(id);
        cardInfo.setCvvCode("216");
        return cardInfo;
    }

    public void unlockSeatPlanForSell(SeatPlan seatPlan) {
        if (seatPlan.isReservedStatus()) {
            SeatPlan seatPlanById = seatPlanService.findSeatPlanById(seatPlan.getId());
            seatPlanById.setReservedStatus(Boolean.FALSE);
            seatPlanService.createAndUpdateSeatPlan(seatPlanById);
        } else {
            throw new AppException("You can not CANCEL...");
        }
    }

    public void lockSeatPlanForSell(SeatPlan seatPlan) {
        if (!seatPlan.isReservedStatus()) {
            SeatPlan seatPlanById = seatPlanService.findSeatPlanById(seatPlan.getId());
            seatPlanById.setReservedStatus(Boolean.TRUE);
            seatPlanService.createAndUpdateSeatPlan(seatPlanById);
        } else {
            throw new AppException("You can not sell buyed seat...");
        }

    }

    private OrderRequestResponse mapOrderRequestResponse(Order order) {
        OrderRequestResponse orderRequestResponse = new OrderRequestResponse();
        orderRequestResponse.setOrderId(order.getId());
        orderRequestResponse.setPassengerIdentification(order.getPassengerIdentification());
        orderRequestResponse.setRouteInformation(order.getRouteInformation());
        return orderRequestResponse;
    }

    private Order mapOrderRequest(OrderRequest orderRequest) {
        Order order = new Order();
        order.getRouteInformation().setSelledTicketNumber(order.getRouteInformation().getSelledTicketNumber() + 1);
        order.setAirCraft(orderRequest.getAirCraft());
        order.setAirport(orderRequest.getAirport());
        order.setIsPaymentOk(orderRequest.getIsPaymentOk());
        order.setPassengerIdentification(orderRequest.getPassengerIdentification());
        order.setRouteInformation(orderRequest.getRouteInformation());
        order.setIsTicketCreated(Boolean.FALSE);
        order.setOrderCode(CommonUtils.generateCode(FLI_CODE));
        return order;
    }

    public void updateOrderPaymentStatus(UUID orderId) {
        Order order = getOrder(orderId);
        order.setIsPaymentOk(Boolean.TRUE);
        orderRepository.save(order);
    }

    public Order getOrder(UUID orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()) {
            throw new AppException("Order not found for this request : " + orderId);
        }
        return orderOptional.get();
    }

    public Order findOrderById(UUID orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()) {
            throw new AppException("Order not found for this request : " + orderId);
        }
        return orderOptional.get();
    }

    public Order deleteOrder(UUID orderId) {
        Order order = findOrderById(orderId);
        order.setEndDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> findAllOrders() {
        List<Order> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(orderList::add);
        return orderList;
    }

}
