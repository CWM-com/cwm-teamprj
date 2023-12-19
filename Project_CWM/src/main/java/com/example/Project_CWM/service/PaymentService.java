package com.example.Project_CWM.service;

import com.example.Project_CWM.dto.PaymentDto;
import com.example.Project_CWM.mappers.PaymentMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

@Service
public class PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;
    private static final String key = "3260770725002265";
    private static final String secretKey = "slRj2br5QBKt1cnKgeCIcvH6x5V0YbexX7jgkXAeDKhDVanqpVDREDxGgDf7NCRAkLyB0kRQksEtcvmL";

    public void getPaymentOrder(PaymentDto paymentDto) {

        String uid = paymentDto.getImpUid();

        if(!uid.isEmpty()) {
            paymentDto.setPaymentStatus("complete");
            paymentMapper.getPaymentOrder(paymentDto);
        }
    }

    public String getToken() throws IOException {
        HttpsURLConnection conn = null;
        URL url = new URL("https://api.iamport.kr/users/getToken");

        conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        JsonObject json = new JsonObject();

        json.addProperty("imp_key",key);
        json.addProperty("imp_secret",secretKey);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write(json.toString()); // json 객체를 문제열 형태로 http 요청에 추가
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(br.readLine(), JsonObject.class);
        String token = jsonResponse.getAsJsonObject("response").get("access_token").getAsString();

        br.close();
        conn.disconnect();
        return token;
    }

    public String ImpPaymentInfo(String impUid, String token) throws IOException {

        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/payments/" + impUid);

        conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", token);
        conn.setDoOutput(true);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(br);
        JsonObject jsonResponse = gson.fromJson(jsonReader, JsonObject.class);

        String ImpAmount = jsonResponse.getAsJsonObject("response").get("amount").getAsString();

        br.close();
        conn.disconnect();


        return ImpAmount;
    }

    public boolean paymentCancle(String impUid, String token, String amount) {
        try {
            String DbAmount = paymentMapper.getPay(impUid); // db에 저장되어 있는 결제값
            DbAmount = DbAmount.replaceAll(",", ""); // 혹시 몰라 DB 금액에서 , 제거
            if(amount != null && amount.equals(DbAmount)) {
                System.out.println(amount + "디비금액입니다.");
                System.out.println(amount + "포트원금액입니다.");
                HttpsURLConnection conn = null;
                URL url = new URL("https://api.iamport.kr/payments/cancel");

                conn = (HttpsURLConnection) url.openConnection();

                conn.setRequestProperty("Content-type","application/json");
                conn.setRequestProperty("Accept","application/json");
                conn.setRequestProperty("Authorization",token);
                conn.setDoOutput(true);

                JsonObject json = new JsonObject();

                json.addProperty("imp_uid",impUid);
                json.addProperty("amount",amount);
                json.addProperty("checksum", amount);

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

                bw.write(json.toString());
                bw.flush();
                bw.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                br.close();
                conn.disconnect();

                if (conn.getResponseCode() == 200) {
                    return true;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
