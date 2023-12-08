package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MapDto;
import com.example.Project_CWM.mappers.MapMapper;
import com.example.Project_CWM.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public String getSearch(Model model, @RequestParam(value="search", defaultValue = "") String search,
                          @RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value="selectType", defaultValue = "") String selectType) {
        model.addAttribute("placeSearch", mapService.getSearch(page, search, selectType));
        model.addAttribute("placeCount", mapService.getSearchCnt(search, selectType));
        model.addAttribute("page", mapService.PageCalc(page));
        return "place/place";
    }
    @GetMapping("/deletePlace")
    @ResponseBody
    public Map<String, Object> deletePlace(@RequestParam String placeCode){
        if(!placeCode.isEmpty()){
            mapService.deletePlace(placeCode);
            mapService.dropFiles(placeCode);
            return Map.of("msg", "success");
        }else{
            return Map.of("msg", "failure");
        }

    }


    @GetMapping("/placedetail")
    public String getPlaceDetail(@RequestParam String placeCode, Model model){
        model.addAttribute("detail", mapService.getDetail(placeCode));
        mapMapper.updateVisit(placeCode);
        return("place/placedetail");
    }


    @GetMapping("/placeregister")
    public String getPlaceRegister(){
        return("place/placeregister");
    }
    @PostMapping("/placeregister")
    public String setPlace(@ModelAttribute MapDto mapDto){
        mapService.setPlace(mapDto);
        mapService.makeFiles(mapDto.getPlaceCode());
        return "redirect:/place/place";
    }
    @GetMapping("/checkPlaceCode")
    @ResponseBody
    public Map<String, Object> getCheckPlaceCode(@RequestParam String placeCode){
        int checkCode = mapService.getCheckPlaceCode(placeCode);
        return Map.of("checkCode", checkCode);
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
