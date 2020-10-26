package com.burakgungor.airlinebooking.repository;

import com.burakgungor.airlinebooking.entity.CardInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardInfoRepository extends CrudRepository<CardInfo, UUID> {
}
