package com.example.Project_CWM.controller;

import com.example.Project_CWM.dto.*;
import com.example.Project_CWM.mappers.PlaceMapper;
import com.example.Project_CWM.service.AdminService;
import com.example.Project_CWM.service.NoticeService;
import com.example.Project_CWM.service.PlaceService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class AdminPageController {
    @Autowired
    private AdminService adminService;
    @Autowired
    PlaceService placeService;
    @Value("${fileDir2}")
    String fileDir2;

    @Autowired
    NoticeService noticeService;

    @GetMapping("")
    public String getAdmin(@RequestParam(value="current", defaultValue="1") String current, Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words, @RequestParam(value="page", defaultValue = "1") int page) {

        model.addAttribute("current", current);
        model.addAttribute("member", adminService.AdminMember());

        List<NoticeDto> notice = noticeService.getNotice();
        model.addAttribute("list", noticeService.getNotice());
        model.addAttribute("cnt", noticeService.getSearchCnt(searchType, words));
        model.addAttribute("list", noticeService.getSearch(page, searchType, words));
        model.addAttribute("page", noticeService.NoticePageCalc(page));

        return "adminPage";
    }

    @GetMapping("current=3")
    public String getPlaceRegister(){
        return("current=3");
    }
    @PostMapping("/current=3")
    public String setPlace(@ModelAttribute PlaceDto placeDto,
                           @ModelAttribute MapDto mapDto,
                           @RequestParam("fileMain") List<MultipartFile> fileMain,
                           @RequestParam("fileDetail") List<MultipartFile> fileDetail,
                           @RequestParam("fileAround") List<MultipartFile> fileAround,
                           String query) throws IOException {
        placeService.setPlace(placeDto);

        String placeCode = placeDto.getPlaceCode();
        String folderName = "place_" + placeCode + "_files";
        String savedPathName = fileDir2 + folderName;
        File makeFolder = new File(fileDir2 + folderName);
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

        //주소 및 좌표
        String apiKey = "9d2f52e8d4518bfba9fd6284df2be14f";
        Float[] coordinate = new Float[2];
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        String jsonString = null;
        query = mapDto.getPlaceAddr();
        try {
            query = URLEncoder.encode(query, "UTF-8");

            String address = apiUrl + "?query=" + query;

            URL url = new URL(address);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);

            BufferedReader rd = null;
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer docJson = new StringBuffer();

            String line;

            while ((line = rd.readLine()) != null) {
                docJson.append(line);
            }

            jsonString = docJson.toString();
            rd.close();

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray documentsArray = jsonObject.getJSONArray("documents");
            JSONObject documentsObject = documentsArray.getJSONObject(0);


            String longtitude = documentsObject.getString("x");
            String latitude = documentsObject.getString("y");

            coordinate[0] = Float.parseFloat(longtitude);
            coordinate[1] = Float.parseFloat(latitude);
            mapDto.setPlaceX(coordinate[0]);
            mapDto.setPlaceY(coordinate[1]);
            Float placeX = mapDto.getPlaceX();
            Float placeY = mapDto.getPlaceY();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        placeService.setAddr(mapDto);

        //세부내용
        placeService.setContent(placeDto);

        return "redirect:/place/place";
    }

    @GetMapping("/checkPlaceCode")
    @ResponseBody
    public Map<String, Object> getCheckPlaceCode(@RequestParam String placeCode){
        int checkCode = placeService.getCheckPlaceCode(placeCode);
        return Map.of("checkCode", checkCode);
    }


}

