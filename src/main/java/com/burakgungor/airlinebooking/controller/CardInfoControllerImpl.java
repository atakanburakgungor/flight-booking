package com.burakgungor.airlinebooking.controller;

import com.burakgungor.airlinebooking.entity.CardInfo;
import com.burakgungor.airlinebooking.service.CardInfoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@CrossOrigin
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CardInfoControllerImpl implements CardInfoController {

    @NonNull
    CardInfoService cardInfoService;

    @Override
    public ResponseEntity<CardInfo> createCardInfo(CardInfo cardInfo) throws Exception {
        CardInfo CardInfoResponse = cardInfoService.createCardInfo(cardInfo);
        return new ResponseEntity<>(CardInfoResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CardInfo> findCardInfoById(UUID cardInfoId) throws Exception {
        CardInfo cardInfo = cardInfoService.findCardInfoById(cardInfoId);
        return new ResponseEntity<>(cardInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CardInfo> deleteCardInfo(UUID cardInfoId) throws Exception {
        CardInfo cardInfo = cardInfoService.deleteCardInfo(cardInfoId);
        return new ResponseEntity<>(cardInfo, HttpStatus.OK);
    }
}
