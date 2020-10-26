package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.Airport;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.repository.AirportRepository;
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
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public Airport findAirportById(UUID airportId) {
        Optional<Airport> airportOptional = airportRepository.findById(airportId);
        if (!airportOptional.isPresent()) {
            throw new AppException("Airport not found for this request : " + airportId);
        }
        return airportOptional.get();
    }

    public Airport deleteAirport(UUID airportId) {
        Airport airport = findAirportById(airportId);
        airport.setEndDate(LocalDateTime.now());
        airportRepository.save(airport);
        return airport;
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public List<Airport> findAllAirports() {
        List<Airport> airportList = new ArrayList<>();
        airportRepository.findAll().forEach(airportList::add);
        return airportList;
    }

    public List<Airport> search(Specification<Airport> specification) {
        return airportRepository.findAll(specification);
    }
}
