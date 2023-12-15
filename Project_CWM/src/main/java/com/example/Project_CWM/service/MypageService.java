package com.example.Project_CWM.service;

import com.example.Project_CWM.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    @Autowired
    private MemberMapper memberMapper;

    public void setinfoUpdate(String userPasswd, String userTel, int idx) {

        if (!userPasswd.isEmpty() && !userTel.isEmpty() && idx != 0) {
            memberMapper.setinfoUpdate(userPasswd,userTel,idx);
        }
    }
}
