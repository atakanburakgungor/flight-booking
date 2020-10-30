package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.AirlineCompany;
import com.burakgungor.airlinebooking.search.CustomRsqlVisitor;
import com.burakgungor.airlinebooking.service.AirlineCompanyService;
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
public class AirlineCompanyControllerImpl implements AirlineCompanyController {

    @NonNull
    private AirlineCompanyService airlineCompanyService;

    @Override
    public ResponseEntity<AirlineCompany> createAirlineCompany(AirlineCompany airlineCompany) {
        AirlineCompany createdAirlineCompany = airlineCompanyService.createAirlineCompany(airlineCompany);
        return new ResponseEntity<>(createdAirlineCompany, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AirlineCompany> findAirlineCompanyById(UUID airlineCompanyId) {
        AirlineCompany createdAirlineCompany = airlineCompanyService.findAirlineCompanyById(airlineCompanyId);
        return new ResponseEntity<>(createdAirlineCompany, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AirlineCompany> deleteAirlineCompany(UUID airlineCompanyId) {
        AirlineCompany airlineCompany = airlineCompanyService.deleteAirlineCompany(airlineCompanyId);
        return new ResponseEntity<>(airlineCompany, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AirlineCompany>> findAllAirlineCompanies() {
        List<AirlineCompany> airlineCompanies = airlineCompanyService.findAllAirlineCompanies();
        return new ResponseEntity<>(airlineCompanies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AirlineCompany>> search(String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<AirlineCompany> spec = rootNode.accept(new CustomRsqlVisitor<>());
        List<AirlineCompany> searches = airlineCompanyService.search(spec);
        return new ResponseEntity<>(searches, HttpStatus.OK);
    }
}
