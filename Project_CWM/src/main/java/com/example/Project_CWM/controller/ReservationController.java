package com.example.Project_CWM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @GetMapping("")
    public String getReservation() {

        return "reservation/reservation";
    }
    @GetMapping("/reservationcheck")
    public String getReservationCheck() {

        return "reservation/reservationcheck";
    }

    @GetMapping("/reservationuseinfo")
    public String getReservationUseinfo() {

        return "reservation/reservationuseinfo";
    }

    @GetMapping("/reservationpaymentinfo")
    public String getRservationPaymentinfo() {

        return "reservation/reservationpaymentinfo";
    }
}
