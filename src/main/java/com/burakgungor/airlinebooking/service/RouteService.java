package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.Route;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route findRouteById(UUID routeId) {
        Optional<Route> routeOptional = routeRepository.findById(routeId);
        if (!routeOptional.isPresent()) {
            throw new AppException("Route not found for this request : " + routeId);
        }
        return routeOptional.get();
    }

    public Route deleteRoute(UUID routeId) {
        Route route = findRouteById(routeId);
        route.setEndDate(LocalDateTime.now());
        return routeRepository.save(route);
    }

    public List<Route> findAllRoutes() {
        List<Route> routeList = new ArrayList<>();
        routeRepository.findAll().forEach(routeList::add);
        return routeList;
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public List<Route> search(Specification<Route> specification) {
        return routeRepository.findAll(specification);
    }
}
