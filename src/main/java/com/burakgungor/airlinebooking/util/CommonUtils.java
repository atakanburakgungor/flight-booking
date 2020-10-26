package com.burakgungor.airlinebooking.util;

import com.burakgungor.airlinebooking.entity.CardInfo;
import com.burakgungor.airlinebooking.model.ValidatePayment;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    private static ObjectMapper objectMapper = null;

    private static Random rand = new SecureRandom();

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
    }


    public static String generateCode(String prefix) {
        Random rand = new SecureRandom();
        int codeFirstValue = rand.nextInt(99999999);
        int codeSecondValue = rand.nextInt(999);
        return prefix + String.format("%08d", codeFirstValue) + "-" + String.format("%03d", codeSecondValue);
    }

    public static Boolean validateCardInfoWithLuhnAlgorithm(CardInfo cardInfo) {
        int sum = 0;
        // delete non digits
        String cardNumber = cardInfo.getCardNumber().replaceAll("\\D+", "");
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public static ValidatePayment validateAndMaskCreditCard(String creditCardNumber) {
        String maskedSentence = null;
        ValidatePayment validatePayment = new ValidatePayment();
        // Visa, MasterCard, Amex, Diners, Discovery, and JCB. REGEX pattern
        Pattern regex = Pattern.compile("\\b(?:4[ -]*(?:\\d[ -]*){11}(?:(?:\\d[ -]*){3})?\\d|"
                + "(?:5[ -]*[1-5](?:[ -]*\\d){2}|(?:2[ -]*){3}[1-9]|(?:2[ -]*){2}[3-9][ -]*"
                + "\\d|2[ -]*[3-6](?:[ -]*\\d){2}|2[ -]*7[ -]*[01][ -]*\\d|2[ -]*7[ -]*2[ -]*0)(?:[ -]*"
                + "\\d){12}|3[ -]*[47](?:[ -]*\\d){13}|3[ -]*(?:0[ -]*[0-5]|[68][ -]*\\d)(?:[ -]*"
                + "\\d){11}|6[ -]*(?:0[ -]*1[ -]*1|5[ -]*\\d[ -]*\\d)(?:[ -]*"
                + "\\d){12}|(?:2[ -]*1[ -]*3[ -]*1|1[ -]*8[ -]*0[ -]*0|3[ -]*5(?:[ -]*"
                + "\\d){3})(?:[ -]*\\d){11})\\b");

        Matcher regexMatcher = regex.matcher(creditCardNumber);

        if (regexMatcher.find()) {
            String creditCard = regexMatcher.group();
            String strippedCreditCard = creditCard.replaceAll("[ -]+", "");
            String subSectionOfCreditCard = strippedCreditCard.substring(6, strippedCreditCard.length() - 4);
            String prefix = strippedCreditCard.substring(0, 6);
            String middle = String.join("", Collections.nCopies(subSectionOfCreditCard.length(), "x"));
            String suffix = strippedCreditCard.substring(strippedCreditCard.length() - 4);
            String maskedCreditCard = prefix + middle + suffix;
            maskedSentence = creditCardNumber.replace(creditCard, maskedCreditCard);
            validatePayment.setIsValidated(Boolean.TRUE);
        } else {
            // If credit card was not found in the text, let's return the original input.
            maskedSentence = creditCardNumber;
            validatePayment.setIsValidated(Boolean.FALSE);
        }
        validatePayment.setCreditCardNumber(creditCardNumber);
        validatePayment.setMaskedCardNumber(maskedSentence);

        return validatePayment;
    }

}
