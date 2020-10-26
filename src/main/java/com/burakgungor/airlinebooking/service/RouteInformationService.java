package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.Route;
import com.burakgungor.airlinebooking.entity.RouteInformation;
import com.burakgungor.airlinebooking.entity.SeatPlan;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.model.OrderRequest;
import com.burakgungor.airlinebooking.repository.RouteInformationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RouteInformationService {

    @Autowired
    RouteInformationRepository routeInformationRepository;

    @Autowired
    SeatPlanService seatPlanService;

    private final static String INCREASE = "INCREASE";
    private final static String DECREASE = "DECREASE";

    public RouteInformation findRouteInformationById(UUID routeInformationId) {
        Optional<RouteInformation> routeInformationOptional = routeInformationRepository.findById(routeInformationId);
        if (!routeInformationOptional.isPresent()) {
            throw new AppException("RouteInformation not found for this request : " + routeInformationId);
        }
        return routeInformationOptional.get();
    }

    private void updateFareBySeatPlan(RouteInformation routeInformation, String operation) {
        List<SeatPlan> seatPlans = seatPlanService.findAllByRouteInformationId(routeInformation.getSeatPlan().get(0).getId());
        for (SeatPlan seats : seatPlans) {
            if (Objects.equals(operation, INCREASE)) {
                seats.getAirFare().setFare(seats.getAirFare().getFare() + ((seats.getAirFare().getFare() * 10) / 100));
            } else {
                seats.getAirFare().setFare(seats.getAirFare().getFare() - ((seats.getAirFare().getFare() * 10) / 100));
            }
            seatPlanService.createAndUpdateSeatPlan(seats);
        }
    }

    public RouteInformation updateRouteInformationStatusById(UUID routeInformationId) {
        RouteInformation routeInformation = findRouteInformationById(routeInformationId);
        return routeInformationRepository.save(routeInformation);
    }

    public RouteInformation deleteRouteInformation(UUID routeInformationId) {
        RouteInformation route = findRouteInformationById(routeInformationId);
        route.setEndDate(LocalDateTime.now());
        return routeInformationRepository.save(route);
    }

    public List<RouteInformation> findAllRouteInformations() {
        List<RouteInformation> routeInformationList = new ArrayList<>();
        routeInformationRepository.findAll().forEach(routeInformationList::add);
        return routeInformationList;
    }

    public RouteInformation createRouteInformation(RouteInformation routeInformation) {
        return routeInformationRepository.save(routeInformation);
    }

    public List<RouteInformation> search(Specification<RouteInformation> specification) {
        return routeInformationRepository.findAll(specification);
    }

    public void incraseCapasity(OrderRequest orderRequest) {
        if ((orderRequest.getRouteInformation().getCapacity() * 10 / 100) == orderRequest.getRouteInformation().getSelledTicketNumber()) {
            updateFareBySeatPlan(orderRequest.getRouteInformation(),INCREASE);
        }
    }

    public void decreaseCapasity(RouteInformation routeInformation) {
        routeInformation.setSelledTicketNumber(routeInformation.getSelledTicketNumber() -1);
        if ((routeInformation.getCapacity() * 10 / 100) > routeInformation.getSelledTicketNumber()) {
            updateFareBySeatPlan(routeInformation,DECREASE);
        }
    }
}
