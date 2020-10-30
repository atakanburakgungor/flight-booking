package com.burakgungor.airlinebooking.service;

import com.burakgungor.airlinebooking.entity.CardInfo;
import com.burakgungor.airlinebooking.entity.Characteristic;
import com.burakgungor.airlinebooking.exception.AppException;
import com.burakgungor.airlinebooking.model.ValidatePayment;
import com.burakgungor.airlinebooking.repository.CardInfoRepository;
import com.burakgungor.airlinebooking.util.CommonUtils;
import com.burakgungor.airlinebooking.util.SecretUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.form.TextareaTag;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CardInfoService {

    @Autowired
    private CardInfoRepository cardInfoRepository;

    private static final String SECRET_KEY = "SECRET_KEY";
    private static final String CIPHER_TEXT = "CIPHER_TEXT";
    private static final String SECURE_IV = "SECURE_IV";
    private static final String CREDIT_CARD_NUMBER = "CREDIT_CARD_NUMBER";

    private Map<String, String> encrypt(String text) throws Exception {
        Map<String, String> hashMap = new HashMap();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        // Generate Key
        SecretKey key = keyGenerator.generateKey();
        // Generating IV.
        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        byte[] cipherText = SecretUtils.encrypt(text.getBytes(), key, IV);
        hashMap.put(CIPHER_TEXT, Base64.getEncoder().encodeToString(cipherText));
        hashMap.put(CREDIT_CARD_NUMBER, text);
        return hashMap;
    }
/*    private String decrpyt(String text, Set<Characteristic> characteristics) throws Exception {

        Optional<Characteristic> secretKeyOpt = characteristics
                .stream()
                .filter(characteristic -> Objects.equals(SECRET_KEY, characteristic.getName())).findAny();
        String secret = secretKeyOpt.get().getValue();
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        Optional<Characteristic> secureIv = characteristics
                .stream()
                .filter(characteristic -> Objects.equals(SECURE_IV, characteristic.getName())).findAny();
        String secureRandomIv = secureIv.get().getValue();
        return SecretUtils.decrypt(text.getBytes(), key, secureRandomIv.getBytes());
    }*/

    public CardInfo findCardInfoById(UUID cardInfoId) throws Exception {
        Optional<CardInfo> cardInfoOptional = cardInfoRepository.findById(cardInfoId);
        if (!cardInfoOptional.isPresent()) {
            throw new AppException("Card Info not found for this request : " + cardInfoId);
        }
        CardInfo cardInfo = cardInfoOptional.get();
        Optional<Characteristic> charValue = cardInfo.getCharacteristics()
                .stream().filter(value -> Objects.equals(CREDIT_CARD_NUMBER, value.getName())).findAny();
        ValidatePayment validatePayment = CommonUtils.validateAndMaskCreditCard(charValue.get().getValue());
        String maskedCardNumber = validatePayment.getMaskedCardNumber();
        cardInfo.setCardNumber(maskedCardNumber);
        return cardInfo;
    }

    public CardInfo deleteCardInfo(UUID cardInfoId) throws Exception {
        CardInfo cardInfo = findCardInfoById(cardInfoId);
        //cardInfo.setEndDate(LocalDateTime.now());
        //return cardInfoRepository.save(cardInfo);
        cardInfoRepository.delete(cardInfo);
        return cardInfo;
    }

    public CardInfo createCardInfo(CardInfo cardInfo) throws Exception {
        Map<String, String> encryptedCardNumberInfo = encrypt(cardInfo.getCardNumber());
        Map<String, String> encryptedCardCvvInfo = encrypt(cardInfo.getCvvCode());
        Map<String, String> encryptedCardHolderNameInfo = encrypt(cardInfo.getCardHolderName());

        Set<Characteristic> characteristicSet = mapCharacteristics(encryptedCardNumberInfo);

        cardInfo.setCvvCode(encryptedCardCvvInfo.get(CIPHER_TEXT));
        cardInfo.setCardNumber(encryptedCardNumberInfo.get(CIPHER_TEXT));
        cardInfo.setCardHolderName(encryptedCardHolderNameInfo.get(CIPHER_TEXT));
        cardInfo.setCharacteristics(characteristicSet);
        return cardInfoRepository.save(cardInfo);
    }

    public Set<Characteristic> mapCharacteristics(Map<String, String> encryptedCardNumberInfo) {
        Set<Characteristic> characteristicSet = new HashSet<>();
        Characteristic characteristicCreditCardNumber = new Characteristic();
        characteristicCreditCardNumber.setValue(encryptedCardNumberInfo.get(CREDIT_CARD_NUMBER));
        characteristicCreditCardNumber.setName(CREDIT_CARD_NUMBER);
        characteristicSet.add(characteristicCreditCardNumber);
        return characteristicSet;
    }

}
