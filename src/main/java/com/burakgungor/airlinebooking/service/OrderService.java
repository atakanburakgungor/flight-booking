package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.*;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.model.OrderRequest;
import com.burakgungor.airlinebooking.model.OrderRequestResponse;
import com.burakgungor.airlinebooking.repository.OrderRepository;
import com.burakgungor.airlinebooking.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

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
    private AirCraftService airCraftService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private PassengerService passengerService;

    private static final String FLI_CODE = "FLI-";

    public OrderRequestResponse createOrderRequest(UUID passengerId, OrderRequest orderRequest) {
        OrderRequestResponse orderRequestResponse = null;
        SeatPlan seatPlan = seatPlanService.findSeatPlanById(orderRequest.getSeatPlanId());
        lockSeatPlanForSell(seatPlan);
        try {
            Order order = mapOrderRequest(passengerId, orderRequest);
            Order savedOrder = orderRepository.save(order);
            orderRequestResponse = mapOrderRequestResponse(savedOrder);
            routeInformationService.incraseCapasity(order.getRouteInformation());
        } catch (Exception e) {
            unlockSeatPlanForSell(seatPlan);
        }
        return orderRequestResponse;
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
            seatPlan.setReservedStatus(Boolean.TRUE);
            seatPlanService.createAndUpdateSeatPlan(seatPlan);
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

    private Order mapOrderRequest(UUID passengerId, OrderRequest orderRequest) {
        Order order = new Order();
        SeatPlan seatPlan = seatPlanService.findSeatPlanById(orderRequest.getSeatPlanId());
        order.setRouteInformation(seatPlan.getRouteInformation());
        order.getRouteInformation().setSelledTicketNumber(order.getRouteInformation().getSelledTicketNumber() + 1);
        AirCraft airCraft = airCraftService.findAirCraftById(orderRequest.getAirCraftId());
        order.setAirCraft(airCraft);
        Airport airport = airportService.findAirportById(orderRequest.getAirportId());
        order.setAirport(airport);
        order.setIsPaymentOk(orderRequest.getIsPaymentOk());
        Passenger passenger = passengerService.findPassengerById(passengerId);
        PassengerIdentification passengerIdentification;
        if (passenger.getType().equals(Passenger.Type.TC)) {
            passengerIdentification = passenger.getPassengerIdentifications()
                    .stream()
                    .filter(identification -> Objects.equals(PassengerIdentification.Type.NATIONAL_ID, identification.getType()))
                    .findAny()
                    .orElse(null);
        } else {
            passengerIdentification = passenger.getPassengerIdentifications()
                    .stream()
                    .filter(identification -> Objects.equals(PassengerIdentification.Type.PASSPORT, identification.getType()))
                    .findAny()
                    .orElse(null);
        }
        order.setPassengerIdentification(passengerIdentification);
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
