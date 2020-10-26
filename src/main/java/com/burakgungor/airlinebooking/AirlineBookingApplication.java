package com.burakgungor.airlinebooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AirlineBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineBookingApplication.class, args);
    }

}
