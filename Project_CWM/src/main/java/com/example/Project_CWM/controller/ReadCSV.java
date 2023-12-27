//package com.example.Project_CWM.controller;
//
//import java.io.*;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// *  CSV 파일을 읽어 들이는 클래스
// *  "C:\\Users\\sunho\\Downloads\\캠핑장 이미지\\한국문화정보원_전국 문화 여가 활동 시설(캠핑) 데이터_20221130.csv"
// */
//public class ReadCSV {
//    public static void main(String[] args) {
//        ReadCSV readCSV = new ReadCSV();
//
//        System.out.println(readCSV.read());
//    }
//
//    public List<List<String>> read(){
//        List<List<String>> csvList = new ArrayList<List<String>>();
//        File csv = new File("C:\\Users\\sunho\\Downloads\\캠핑장 이미지\\한국문화정보원_전국 문화 여가 활동 시설(캠핑) 데이터_20221130.csv");
//        BufferedReader br = null;
//        String line = "";
//
//        try{
//            br = new BufferedReader(new FileReader(csv));
//            while((line = br.readLine()) != null){
//                List<String> aLine = new ArrayList<String>();
//                String[] lineArr = line.split(",");
//                aLine = Arrays.asList(lineArr);
//                csvList.add(aLine);
//            }
//        }catch(FileNotFoundException e){
//            e.printStackTrace();
//        }catch(IOException e){
//            e.printStackTrace();
//        }finally {
//            try{
//                if(br != null){
//                    br.close();
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        return csvList;
//    }
//}