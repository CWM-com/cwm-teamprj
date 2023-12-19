package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MapDto;
import com.example.Project_CWM.mappers.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/place")
public class MapController {

    @Autowired
    MapMapper mapMapper;

    @GetMapping("/map")
    public String getMap() {
        return("place/map");
    }

    @GetMapping("/map/mapList")
    @ResponseBody
    public Map<String, Object> getCommentList(@ModelAttribute MapDto mapDto){
        List<MapDto> mList = mapMapper.getMapList(mapDto);
        return Map.of("mList", mList);
    }
}
