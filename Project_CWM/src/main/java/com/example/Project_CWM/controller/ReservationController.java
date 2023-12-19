package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.dto.ReservSearchDto;
import com.example.Project_CWM.dto.ReservationOrderDto;
import com.example.Project_CWM.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("")
    public String getReservation(HttpSession session, Model model) {

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn");

        if(setIDX != null) {
            int Idx = setIDX.getIdx();
            model.addAttribute("userIdx",Idx);
        }
        return "reservation/reservation";
    }
    @GetMapping("/reservationcheck")
    public String getReservationCheck(HttpSession session, Model model) {

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn"); // 세션값 부여

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

    @GetMapping("/reservationsearch")
    @ResponseBody
    public Map<String,Object> getReservationSearch(ReservSearchDto reservSearchDto) {

        Map<String,Object> map = new HashMap<>();

        int count  =  reservationService.getCount(reservSearchDto);

        map.put("cnt",count);
        map.put("search",reservationService.getSearchType(reservSearchDto));
        map.put("price", reservationService.getPrice(reservSearchDto));


        return map;
    }

    @PostMapping("/reservationOrder")
    public ModelAndView reservationOrder(ReservationOrderDto reservationOrderDto, HttpSession session) {

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn");

        if(setIDX != null) {

            int day = Integer.parseInt(reservationOrderDto.getCheckdays());
            int price = Integer.parseInt(reservationOrderDto.getCampPrice());
            int cnt = day * price;

            NumberFormat numberFormat = NumberFormat.getNumberInstance();

            String res = numberFormat.format(cnt);

            reservationOrderDto.setCampPrice(res);
            ModelAndView mv = new ModelAndView();

            mv.setViewName("/reservation/reservationcheck");
            mv.addObject("data", reservationOrderDto);
            return mv;
        }else {
            ModelAndView vm = new ModelAndView("redirect:/login");
            return vm;
        }
    }

    @PostMapping("/reservList")
    @ResponseBody
    public Map<String,Object> getReservationList(ReservationOrderDto reservationOrderDto, HttpSession session) {

        reservationService.getReservationList(reservationOrderDto);
        Map<String,Object> map = new HashMap<>();

        MemberDto setIDX = (MemberDto) session.getAttribute("LoginIn");
        
        if(reservationOrderDto.getReservStatus().equals("complete") && setIDX != null) {
            map.put("msg","success");
        }
        return map;
    }
}
