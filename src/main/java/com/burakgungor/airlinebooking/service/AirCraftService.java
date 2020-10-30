package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.AirCraft;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.repository.AirCraftRepository;
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
public class AirCraftService {

    @Autowired
    private AirCraftRepository airCraftRepository;

    public AirCraft findAirCraftById(UUID aircraftId) {
        Optional<AirCraft> airCraftOptional = airCraftRepository.findById(aircraftId);
        if (!airCraftOptional.isPresent()) {
            throw new AppException("AirCraft not found for this request : " + aircraftId);
        }
        return airCraftOptional.get();
    }

}
