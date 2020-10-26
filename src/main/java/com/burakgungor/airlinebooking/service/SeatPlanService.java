package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.SeatPlan;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.repository.SeatPlanRepository;
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
public class SeatPlanService {

    @Autowired
    SeatPlanRepository seatPlanRepository;

    public SeatPlan findSeatPlanById(UUID saetPlanId) {
        Optional<SeatPlan> saetPlanOptional = seatPlanRepository.findById(saetPlanId);
        if (!saetPlanOptional.isPresent()) {
            throw new AppException("SeatPlan not found for this request : " + saetPlanId);
        }
        return saetPlanOptional.get();
    }

    public SeatPlan updateSeatPlanStatusById(UUID saetPlanId) {
        SeatPlan seatPlan = findSeatPlanById(saetPlanId);
        seatPlan.setEndDate(LocalDateTime.now());
        return seatPlanRepository.save(seatPlan);
    }

    public SeatPlan deleteSeatPlan(UUID saetPlanId) {
        SeatPlan seatPlan = findSeatPlanById(saetPlanId);
        seatPlan.setEndDate(LocalDateTime.now());
        return seatPlanRepository.save(seatPlan);
    }

    public List<SeatPlan> findAllSeatPlans() {
        List<SeatPlan> seatPlanList = new ArrayList<>();
        seatPlanRepository.findAll().forEach(seatPlanList::add);
        return seatPlanList;
    }

    public SeatPlan createAndUpdateSeatPlan(SeatPlan seatPlan) {
        return seatPlanRepository.save(seatPlan);
    }

    public List<SeatPlan> search(Specification<SeatPlan> specification) {
        return seatPlanRepository.findAll(specification);
    }


    public List<SeatPlan> findAllByRouteInformationId(UUID routeInformationId) {
        return seatPlanRepository.findAllByRouteInformationId(routeInformationId);
    }
}
