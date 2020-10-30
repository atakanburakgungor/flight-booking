package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.Order;
import com.burakgungor.airlinebooking.model.OrderRequest;
import com.burakgungor.airlinebooking.model.OrderRequestResponse;
import com.burakgungor.airlinebooking.service.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@CrossOrigin
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderRequestControllerImpl implements OrderRequestController {

    @NonNull
    OrderService orderService;

    @Override
    public ResponseEntity<OrderRequestResponse> createOrderRequest(UUID passengerId,OrderRequest orderRequest) {
        OrderRequestResponse orderRequestResponse = orderService.createOrderRequest(passengerId,orderRequest);
        return new ResponseEntity<>(orderRequestResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> findOrderRequestById(UUID orderRequestId) {
        Order order = orderService.findOrderById(orderRequestId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> deleteOrderRequest(UUID orderRequestId) {
        Order order = orderService.deleteOrder(orderRequestId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Order>> findAllOrderRequests() {
        List<Order> order = orderService.findAllOrders();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
