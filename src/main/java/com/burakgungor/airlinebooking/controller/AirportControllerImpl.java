package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.Airport;
import com.burakgungor.airlinebooking.entity.Route;
import com.burakgungor.airlinebooking.search.CustomRsqlVisitor;
import com.burakgungor.airlinebooking.service.AirportService;
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
public class AirportControllerImpl implements AirportController {

    @NonNull
    private AirportService airportService;

    @Override
    public ResponseEntity<Airport> createAirport(Airport Airport) {
        Airport createdAirport = airportService.createAirport(Airport);
        return new ResponseEntity<>(createdAirport, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Airport> findAirportById(UUID airportId) {
        Airport createdAirport = airportService.findAirportById(airportId);
        return new ResponseEntity<>(createdAirport, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Airport> deleteAirport(UUID airportId) {
        Airport deletedAirport = airportService.deleteAirport(airportId);
        return new ResponseEntity<>(deletedAirport, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Airport>> findAllAirports() {
        List<Airport> Airports = airportService.findAllAirports();
        return new ResponseEntity<>(Airports, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Airport>> search(String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Airport> spec = rootNode.accept(new CustomRsqlVisitor<>());
        List<Airport> searches = airportService.search(spec);
        return new ResponseEntity<>(searches, HttpStatus.OK);
    }
}
