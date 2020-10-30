package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.Passenger;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger findPassengerById(UUID passengerId) {
        Optional<Passenger> passengerOptional = passengerRepository.findById(passengerId);
        if (!passengerOptional.isPresent()) {
            throw new AppException("Passenger not found for this request : " + passengerId);
        }
        return passengerOptional.get();
    }

}
