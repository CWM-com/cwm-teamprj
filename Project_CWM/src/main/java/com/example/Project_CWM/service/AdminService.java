package com.example.Project_CWM.service;

import com.example.Project_CWM.dto.MemberDto;
import com.example.Project_CWM.dto.PageDto;
import com.example.Project_CWM.mappers.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public List<MemberDto> AdminMember() {
        return adminMapper.AdminMember();
    }

}
