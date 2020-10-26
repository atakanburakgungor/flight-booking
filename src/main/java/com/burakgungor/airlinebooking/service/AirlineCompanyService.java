package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.AirlineCompany;
import com.burakgungor.airlinebooking.entity.Airport;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.repository.AirlineCompanyRepository;
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
public class AirlineCompanyService {

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    public AirlineCompany findAirlineCompanyById(UUID airlineCompanyId) {
        Optional<AirlineCompany> airlineCompanyOptional = airlineCompanyRepository.findById(airlineCompanyId);
        if (!airlineCompanyOptional.isPresent()) {
            throw new AppException("AirlineCompany not found for this request : " + airlineCompanyId);
        }
        return airlineCompanyOptional.get();
    }

    public AirlineCompany createAirlineCompany(AirlineCompany airlineCompany) {
        return airlineCompanyRepository.save(airlineCompany);
    }

    public AirlineCompany deleteAirlineCompany(UUID airlineCompanyId) {
        AirlineCompany airlineCompany = findAirlineCompanyById(airlineCompanyId);
        airlineCompany.setEndDate(LocalDateTime.now());
        airlineCompanyRepository.save(airlineCompany);
        //airlineCompanyRepository.delete(airlineCompany);
        return airlineCompany;
    }

    public List<AirlineCompany> findAllAirlineCompanies() {
        List<AirlineCompany> airlineCompanies = new ArrayList<>();
        airlineCompanyRepository.findAll().forEach(airlineCompanies::add);
        return airlineCompanies;
    }
    public List<AirlineCompany> search(Specification<AirlineCompany> specification) {
        return airlineCompanyRepository.findAll(specification);
    }
}
