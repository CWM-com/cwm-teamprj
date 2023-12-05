package com.example.Project_CWM.Service;

import com.example.Project_CWM.mappers.SigninMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SigninService {

    @Autowired
    private SigninMapper signinMapper;

    public String setID(String userName,String userEmail) {

        String result = "";

        if(signinMapper.setFindResult(userName, userEmail) != null) {
            result = signinMapper.setFindResult(userName, userEmail);
        }else {
            System.out.println("등록된 회원정보가 없습니다.");
        }
            return result;
    }
}
