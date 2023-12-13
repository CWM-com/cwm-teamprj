package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.MapDto;
import com.example.Project_CWM.dto.MapFilesDto;
import com.example.Project_CWM.mappers.MapMapper;
import com.example.Project_CWM.service.MapService;
import com.fasterxml.jackson.core.io.MergedStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    public String getSearch(Model model,
                          @RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "selectType", defaultValue = "") String selectType,
                          @RequestParam(value="search", defaultValue = "") String search){
        model.addAttribute("placeSearch", mapService.getSearch(page, selectType, search));
        model.addAttribute("page", mapService.PageCalc(page, selectType, search));

        String searchQuery = mapService.selectSearch(selectType, search);
        model.addAttribute("total", mapMapper.getSearchCount(searchQuery));

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
        if(file.exists()){ //파일존재여부확인
            if(file.isDirectory()){ //파일이 디렉토리인지 확인
                File[] files = file.listFiles();
                for(int i = 0; i < files.length; i++){
                    files[i].delete();
                }
            }
            file.delete();
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
        return("redirect:/place/place");
    }


    @GetMapping("/placedetail")
    public String getPlaceDetail(@RequestParam String placeCode, Model model){
        mapMapper.updateVisit(placeCode);
        model.addAttribute("detail", mapService.getDetail(placeCode));
        return("place/placedetail");
    }
    @GetMapping("/placedetail/detailList")
    @ResponseBody
    public Map<String, Object> getDetailList(@RequestParam String placeCode){
        List<MapFilesDto> dList = mapMapper.getDetailFiles(placeCode);
        return Map.of("cList", dList);
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

        for(MultipartFile mf : fileMain){
            String realName = mf.getOriginalFilename();
            String ext = realName.substring(realName.lastIndexOf("."));
            String orgName = "camp_" + placeCode + "_main" + ext;
            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + ext;
            String fileType = "main";
            File saveFile = new File(savedPathName, orgName);

            mf.transferTo(saveFile);

            //섬네일 파일 생성
            File thumbnailFile = new File((savedPathName + "/" + "thumb_" + orgName));
            BufferedImage thumb = ImageIO.read(saveFile);
            /* 비율 */
            double ratio = 3;
            int width = (int) (thumb.getWidth() / ratio);
            int height = (int) (thumb.getHeight() / ratio);
            // 생성자 매개변수 넓이, 높이, 생성될 이미지 타입
            //실제 이미지 사이즈
            BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = bt_image.createGraphics();
            //출력될 이미지 사이즈
            graphic.drawImage(thumb, 0, 0, width, height, null);
            ImageIO.write(bt_image, "jpg", thumbnailFile);

            mapFilesDto.setPlaceCode(placeCode);
            mapFilesDto.setFileType(fileType);
            mapFilesDto.setOrgName(orgName);
            mapFilesDto.setSavedFileName(savedFileName);
            mapFilesDto.setSavedPathName(savedPathName);
            mapFilesDto.setFolderName(folderName);
            mapFilesDto.setExt(ext);

            mapService.setFiles(mapFilesDto);
        }

        if(!fileDetail.get(0).isEmpty()) {
            for (int i = 0; i < fileDetail.size(); i++) {
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
        }
        if(!fileAround.get(0).isEmpty()){
            for (int i = 0; i < fileAround.size(); i++) {
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
