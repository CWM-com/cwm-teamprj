package com.example.Project_CWM.service;

import com.example.Project_CWM.dto.ReservSearchDto;
import com.example.Project_CWM.dto.ReservationOrderDto;
import com.example.Project_CWM.mappers.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    public List<String> getSearchType(ReservSearchDto reservSearchDto) {

        List<ReservSearchDto> searchList = reservationMapper.getSearchType(reservSearchDto);


        List<String> campTypes = searchList.stream()
                .map(ReservSearchDto::getCamptype)
                .collect(Collectors.toList());

        return campTypes;
    }

    public int getCount(ReservSearchDto reservSearchDto) {

        return reservationMapper.getCount(reservSearchDto);
    }

    public List<Integer> getPrice(ReservSearchDto reservSearchDto) {

        List<ReservSearchDto> searchPrice = reservationMapper.getPrice(reservSearchDto);

        List<Integer> campPrice = searchPrice.stream()
                        .map(ReservSearchDto::getCampPrice)
                                .collect(Collectors.toList());

        return campPrice;
    }

    public void getReservationList(ReservationOrderDto reservationOrderDto) {

        if(reservationOrderDto.getReservStatus() == null) {
                reservationOrderDto.setReservStatus("complete");
                reservationMapper.getReservationList(reservationOrderDto);
        }
    }




}
