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
        model.addAttribute("main", mapService.getMainFiles());


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
    @GetMapping("/deleteFiles")
    public String deleteFiles(@RequestParam String placeCode){
        String folderName = "place_" + placeCode + "_files";
        File file = new File(fileDir + folderName);
        if( file.exists() ){ //파일존재여부확인
            if(file.isDirectory()){ //파일이 디렉토리인지 확인
                File[] files = file.listFiles();
                for(int i = 0; i < files.length; i++){
                    if(files[i].delete()){
                        System.out.println(files[i].getName()+" 삭제성공");
                    }else{
                        System.out.println(files[i].getName()+" 삭제실패");
                    }
                }
            }
            if(file.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
        return("redirect:/place/place");
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
                           @RequestParam("fileMain") List<MultipartFile> fileMain,
                           @RequestParam("fileDetail") List<MultipartFile> fileDetail,
                           @RequestParam("fileAround") List<MultipartFile> fileAround) throws IOException {
        mapService.setPlace(mapDto);

        String placeCode = mapDto.getPlaceCode();
        String folderName = "place_" + placeCode + "_files";
        String savedPathName = fileDir + folderName;
        File makeFolder = new File(fileDir + folderName);
        if(!makeFolder.exists()){
            makeFolder.mkdir();
        }
        MapFilesDto mapFilesDto = new MapFilesDto();

        for(int i = 0; i < fileMain.size(); i++){
            String realName = fileMain.get(i).getOriginalFilename();
            String ext = realName.substring(realName.lastIndexOf("."));
            String orgName = "camp_" + placeCode + "_main" + ext;
            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + ext;
            String fileType = "main";

            fileMain.get(i).transferTo(new File(savedPathName + "/" + orgName));

            mapFilesDto.setPlaceCode(placeCode);
            mapFilesDto.setFileType(fileType);
            mapFilesDto.setOrgName(orgName);
            mapFilesDto.setSavedFileName(savedFileName);
            mapFilesDto.setSavedPathName(savedPathName);
            mapFilesDto.setFolderName(folderName);
            mapFilesDto.setExt(ext);

            mapService.setFiles(mapFilesDto);
        }
        for(int i = 0; i < fileDetail.size(); i++){
            String realName = fileDetail.get(i).getOriginalFilename();
            String ext = realName.substring(realName.lastIndexOf("."));
            String orgName = "camp_" + placeCode + "_detail_" + i + ext;
            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + ext;
            String fileType = "detail";

            fileDetail.get(i).transferTo(new File(savedPathName + "/" + orgName));

            mapFilesDto.setPlaceCode(placeCode);
            mapFilesDto.setFileType(fileType);
            mapFilesDto.setOrgName(orgName);
            mapFilesDto.setSavedFileName(savedFileName);
            mapFilesDto.setSavedPathName(savedPathName);
            mapFilesDto.setFolderName(folderName);
            mapFilesDto.setExt(ext);

            mapService.setFiles(mapFilesDto);
        }
        for(int i = 0; i < fileAround.size(); i++){
            String realName = fileAround.get(i).getOriginalFilename();
            String ext = realName.substring(realName.lastIndexOf("."));
            String orgName = "camp_" + placeCode + "_around_" + i + ext;
            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + ext;
            String fileType = "around";

            fileAround.get(i).transferTo(new File(savedPathName + "/" + orgName));

            mapFilesDto.setPlaceCode(placeCode);
            mapFilesDto.setFileType(fileType);
            mapFilesDto.setOrgName(orgName);
            mapFilesDto.setSavedFileName(savedFileName);
            mapFilesDto.setSavedPathName(savedPathName);
            mapFilesDto.setFolderName(folderName);
            mapFilesDto.setExt(ext);

            mapService.setFiles(mapFilesDto);
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
