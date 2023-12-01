package com.example.Project_CWM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
    @GetMapping("/place/map")
    public String getMap(){
        return "place/map";
    }

    @GetMapping("/place/place")
    public String getPlace(){
        return "place/place";
    }

    @GetMapping("/place/placedetail")
    public String getPlaceDetail(){
        return "place/placedetail";
    }

    @GetMapping("/place/placetool")
    public String getPlaceTip(){
        return "place/placetool";
    }

    @GetMapping("/place/placefood")
    public String getPlaceFood(){
        return "place/placefood";
    }

    @GetMapping("/place/placedelivery")
    public String getPlaceDelivery(){
        return "place/placedelivery";
    }
}
