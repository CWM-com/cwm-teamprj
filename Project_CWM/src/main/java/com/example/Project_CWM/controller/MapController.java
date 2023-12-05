package com.example.Project_CWM.controller;

import com.example.Project_CWM.mappers.MapMapper;
import com.example.Project_CWM.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/place")
public class MapController {

    @Autowired
    MapService mapService;

    @Autowired
    MapMapper mapMapper;

    @GetMapping("/map")
    public String getMap(){
        return("place/map");
    }

    @GetMapping("/place")
    public String getList(Model model, @RequestParam(value="search", defaultValue = "") String search,
                          @RequestParam(value = "page", defaultValue = "1") int page) {
//        model.addAttribute("list", mapService.getSearch(page, words));
        model.addAttribute("cnt", mapService.getSearchCnt(search));
        return "place/place";
    }

    @GetMapping("/placedetail")
    public String getPlaceDetail(){
        return("place/placedetail");
    }

    @GetMapping("/placeregister")
    public String getPlaceRegister(){
        return("place/placeregister");
    }

    @GetMapping("/placedelivery")
    public String getPlaceDelivery(){
        return("place/placedelivery");
    }

    @GetMapping("/placefood")
    public String getPlaceFood(){
        return("place/placefood");
    }

    @GetMapping("/placetool")
    public String getPlaceTool(){
        return("place/placetool");
    }
}
