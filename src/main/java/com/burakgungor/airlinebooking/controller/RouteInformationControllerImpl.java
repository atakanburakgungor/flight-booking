package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.RouteInformation;
import com.burakgungor.airlinebooking.search.CustomRsqlVisitor;
import com.burakgungor.airlinebooking.service.RouteInformationService;
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
public class RouteInformationControllerImpl implements RouteInformationController {

    @NonNull
    private RouteInformationService routeInformationService;

    @Override
    public ResponseEntity<RouteInformation> createRouteInformation(UUID airlineCompanyId,RouteInformation routeInformation) {
        RouteInformation createdRouteInformation = routeInformationService.createRouteInformation(airlineCompanyId,routeInformation);
        return new ResponseEntity<>(createdRouteInformation, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RouteInformation> findRouteInformationById(UUID routeInformationId) {
        RouteInformation createdRouteInformation = routeInformationService.findRouteInformationById(routeInformationId);
        return new ResponseEntity<>(createdRouteInformation, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RouteInformation> deleteRouteInformation(UUID routeInformationId) {
        RouteInformation deletedRouteInformation = routeInformationService.deleteRouteInformation(routeInformationId);
        return new ResponseEntity<>(deletedRouteInformation, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RouteInformation>> findAllRouteInformations() {
        List<RouteInformation> RouteInformations = routeInformationService.findAllRouteInformations();
        return new ResponseEntity<>(RouteInformations, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RouteInformation>> search(String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<RouteInformation> spec = rootNode.accept(new CustomRsqlVisitor<>());
        List<RouteInformation> searches = routeInformationService.search(spec);
        return new ResponseEntity<>(searches, HttpStatus.OK);
    }
}
