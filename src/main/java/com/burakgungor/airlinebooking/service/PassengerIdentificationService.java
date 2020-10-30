package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.PassengerIdentification;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.repository.PassengerIdentificationRepository;
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
public class PassengerIdentificationService {

    @Autowired
    private PassengerIdentificationRepository passengerIdentificationRepository;

    public PassengerIdentification findPassengerIdentificationById(UUID passengerIdentificationId) {
        Optional<PassengerIdentification> passengerIdentificationOptional = passengerIdentificationRepository.findById(passengerIdentificationId);
        if (!passengerIdentificationOptional.isPresent()) {
            throw new AppException("PassengerIdentification not found for this request : " + passengerIdentificationId);
        }
        return passengerIdentificationOptional.get();
    }

}
