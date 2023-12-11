package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MapDto;
import com.example.Project_CWM.dto.MapFilesDto;
import com.example.Project_CWM.mappers.MapMapper;
import com.example.Project_CWM.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/place")
public class MapController {

    @Autowired
    MapService mapService;
    @Autowired
    MapMapper mapMapper;
    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/map")
    public String getMap(){
        return("place/map");
    }


    @GetMapping("/place")
    public String getSearch(Model model, @RequestParam(value="search", defaultValue = "") String search,
                          @RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "selectType", defaultValue = "") String selectType){
        model.addAttribute("placeSearch", mapService.getSearch(page, search, selectType));
        model.addAttribute("placeCount", mapService.getSearchCnt(search, selectType));
        model.addAttribute("page", mapService.PageCalc(page));
        model.addAttribute("main", mapService.getFiles());


        return "place/place";
    }
    @GetMapping("/deletePlace")
    @ResponseBody
    public Map<String, Object> deletePlace(@RequestParam String placeCode){
        if(!placeCode.isEmpty()){
            mapService.dropFiles(placeCode);
            mapService.deletePlace(placeCode);
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
    public String setPlace(@ModelAttribute MapDto mapDto,
                           @RequestParam("picMain") List<MultipartFile> fileMain) throws IOException {
        mapService.setPlace(mapDto);
        if(!fileMain.get(0).isEmpty()){
            String placeCode = mapDto.getPlaceCode();
            String folderName = "place_" + placeCode + "_files";
            File makeFolder = new File(fileDir + folderName);
            if(!makeFolder.exists()){
                makeFolder.mkdir();
            }
            MapFilesDto mapFilesDto = new MapFilesDto();
            for(MultipartFile mf : fileMain){
                String savedPathName = fileDir + folderName;
                String realName = mf.getOriginalFilename();
                String ext = realName.substring(realName.lastIndexOf("."));
                String orgName = "camp_" + placeCode + "_main" + ext;
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + ext;

                mf.transferTo(new File(savedPathName + "/" + orgName));

                mapFilesDto.setPlaceCode(placeCode);
                mapFilesDto.setOrgName(orgName);
                mapFilesDto.setSavedFileName(savedFileName);
                mapFilesDto.setSavedPathName(savedPathName);
                mapFilesDto.setFolderName(folderName);
                mapFilesDto.setExt(ext);

                mapService.setFiles(mapFilesDto);
            }
        }

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
