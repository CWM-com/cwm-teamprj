package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.*;
import com.example.Project_CWM.mappers.PlaceMapper;
import com.example.Project_CWM.service.PlaceService;
import jakarta.servlet.http.HttpSession;
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
public class PlaceController {

    @Autowired
    PlaceService placeService;
    @Autowired
    PlaceMapper placeMapper;
    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/place")
    public String getSearch(Model model,
                          @RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "selectType", defaultValue = "") String selectType,
                          @RequestParam(value="search", defaultValue = "") String search){
        model.addAttribute("placeSearch", placeService.getSearch(page, selectType, search));
        model.addAttribute("page", placeService.PageCalc(page, selectType, search));

        String searchQuery = placeService.selectSearch(selectType, search);
        model.addAttribute("total", placeMapper.getSearchCount(searchQuery));

        model.addAttribute("main", placeService.getMainFiles());
        return "place/place";
    }
    @GetMapping("/deletePlace")
    @ResponseBody
    public Map<String, Object> deletePlace(@RequestParam String placeCode){
        if(!placeCode.isEmpty()){
            placeService.dropFiles(placeCode);
            placeService.deletePlace(placeCode);
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
        placeMapper.updateVisit(placeCode);
        model.addAttribute("detail", placeService.getDetail(placeCode));
        return("place/placedetail");
    }
    @PostMapping("/placedetail/addBookmark")
    @ResponseBody
    public Map<String, Object> addBookmark(String placeCode, int idx){
        placeService.addBookmark(placeCode, idx);
        return Map.of("msg", "success");
    }
    @PostMapping("/placedetail/delBookmark")
    @ResponseBody
    public Map<String, Object> delBookmark(String placeCode, int idx){
        placeService.delBookmark(placeCode, idx);
        return Map.of("msg", "success");
    }



    @GetMapping("/placeregister")
    public String getPlaceRegister(){
        return("place/placeregister");
    }
    @PostMapping("/placeregister")
    public String setPlace(@ModelAttribute PlaceDto placeDto,
                           @ModelAttribute MapDto mapDto,
                           @RequestParam("fileMain") List<MultipartFile> fileMain,
                           @RequestParam("fileDetail") List<MultipartFile> fileDetail,
                           @RequestParam("fileAround") List<MultipartFile> fileAround) throws IOException {
        placeService.setPlace(placeDto);
        placeService.setAddr(mapDto);

        String placeCode = placeDto.getPlaceCode();
        String folderName = "place_" + placeCode + "_files";
        String savedPathName = fileDir + folderName;
        File makeFolder = new File(fileDir + folderName);
        if(!makeFolder.exists()){
            makeFolder.mkdir();
        }
        PlaceFilesDto placeFilesDto = new PlaceFilesDto();
        //메인파일 및 섬네일
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

            placeFilesDto.setPlaceCode(placeCode);
            placeFilesDto.setFileType(fileType);
            placeFilesDto.setOrgName(orgName);
            placeFilesDto.setSavedFileName(savedFileName);
            placeFilesDto.setSavedPathName(savedPathName);
            placeFilesDto.setFolderName(folderName);
            placeFilesDto.setExt(ext);

            placeService.setFiles(placeFilesDto);
        }
        //디테일 파일 및 섬네일
        if(!fileDetail.get(0).isEmpty()) {
            for (int i = 0; i < fileDetail.size(); i++) {
                String realName = fileDetail.get(i).getOriginalFilename();
                String ext = realName.substring(realName.lastIndexOf("."));
                String orgName = "camp_" + placeCode + "_detail_" + (i + 1) + ext;
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + ext;
                String fileType = "detail";
                File saveFile = new File(savedPathName, orgName);

                fileDetail.get(i).transferTo(saveFile);

                //섬네일 파일 생성
                File thumbnailFile = new File((savedPathName + "/" + "thumb_" + orgName));
                BufferedImage thumb = ImageIO.read(saveFile);
                /* 비율 */
                double ratio = 1.5;
                int width = (int) (thumb.getWidth() / ratio);
                int height = (int) (thumb.getHeight() / ratio);
                // 생성자 매개변수 넓이, 높이, 생성될 이미지 타입
                //실제 이미지 사이즈
                BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                Graphics2D graphic = bt_image.createGraphics();
                //출력될 이미지 사이즈
                graphic.drawImage(thumb, 0, 0, width, height, null);
                ImageIO.write(bt_image, "jpg", thumbnailFile);

                placeFilesDto.setPlaceCode(placeCode);
                placeFilesDto.setFileType(fileType);
                placeFilesDto.setOrgName(orgName);
                placeFilesDto.setSavedFileName(savedFileName);
                placeFilesDto.setSavedPathName(savedPathName);
                placeFilesDto.setFolderName(folderName);
                placeFilesDto.setExt(ext);

                placeService.setFiles(placeFilesDto);
            }
        }
        //주변 파일 및 섬네일
        if(!fileAround.get(0).isEmpty()){
            for (int i = 0; i < fileAround.size(); i++) {
                String realName = fileAround.get(i).getOriginalFilename();
                String ext = realName.substring(realName.lastIndexOf("."));
                String orgName = "camp_" + placeCode + "_around_" + (i + 1) + ext;
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + ext;
                String fileType = "around";
                File saveFile = new File(savedPathName, orgName);

                fileAround.get(i).transferTo(saveFile);

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

                placeFilesDto.setPlaceCode(placeCode);
                placeFilesDto.setFileType(fileType);
                placeFilesDto.setOrgName(orgName);
                placeFilesDto.setSavedFileName(savedFileName);
                placeFilesDto.setSavedPathName(savedPathName);
                placeFilesDto.setFolderName(folderName);
                placeFilesDto.setExt(ext);

                placeService.setFiles(placeFilesDto);
            }
        }


        return "redirect:/place/place";
    }
    @GetMapping("/checkPlaceCode")
    @ResponseBody
    public Map<String, Object> getCheckPlaceCode(@RequestParam String placeCode){
        int checkCode = placeService.getCheckPlaceCode(placeCode);
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
