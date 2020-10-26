package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.Route;
import com.burakgungor.airlinebooking.search.CustomRsqlVisitor;
import com.burakgungor.airlinebooking.service.RouteService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
public class RouteControllerImpl implements RouteController {

    @NonNull
    private RouteService routeService;

    @Override
    public ResponseEntity<Route> createRoute(Route Route) {
        Route createdRoute = routeService.createRoute(Route);
        return new ResponseEntity<>(createdRoute, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Route> findRouteById(UUID routeId) {
        Route createdRoute = routeService.findRouteById(routeId);
        return new ResponseEntity<>(createdRoute, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Route> deleteRoute(UUID routeId) {
        Route deletedRoute = routeService.deleteRoute(routeId);
        return new ResponseEntity<>(deletedRoute, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Route>> findAllRoutes() {
        List<Route> Routes = routeService.findAllRoutes();
        return new ResponseEntity<>(Routes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Route>> search(String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Route> spec = rootNode.accept(new CustomRsqlVisitor<>());
        List<Route> searches = routeService.search(spec);
        return new ResponseEntity<>(searches, HttpStatus.OK);
    }
}
