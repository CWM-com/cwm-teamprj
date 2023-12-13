package com.example.Project_CWM.Service;

import com.example.Project_CWM.dto.NoticeDto;
import com.example.Project_CWM.mappers.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    public List<NoticeDto> getNotice() {
        return noticeMapper.getNotice();
    }

    public void setWrite(NoticeDto noticeDto) {
        noticeMapper.setWrite(noticeDto);
    }
}
