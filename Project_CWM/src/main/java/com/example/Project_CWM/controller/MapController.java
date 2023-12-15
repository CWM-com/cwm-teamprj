package com.example.Project_CWM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Controller
@RequestMapping("/place")
public class MapController {
    @GetMapping("/map")
    public String getKakaoApiFromAddress(String roadFullAddr) {
        String apiKey = "9d2f52e8d4518bfba9fd6284df2be14f";
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        String jsonString = null;
        roadFullAddr = "충남 태안군 이원면 꾸지나무길 37-78";
        System.out.println("roadFullAddr1 : " + roadFullAddr);
        try {
            roadFullAddr = URLEncoder.encode(roadFullAddr, "UTF-8");
            System.out.println("roadFullAddr2 : " + roadFullAddr);

            String addr = apiUrl + "?query=" + roadFullAddr;
            System.out.println("addr : " + addr);

            URL url = new URL(addr);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);
            System.out.println("conn : " + conn);

            BufferedReader rd = null;
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer docJson = new StringBuffer();
            System.out.println("rd : " + rd);
            System.out.println("docJson : " + docJson);

            String line;

            while ((line=rd.readLine()) != null) {
                docJson.append(line);
                System.out.println(docJson.append(line));
            }

            jsonString = docJson.toString();
            rd.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return("place/map");
    }
}
