package com.dw.artgallery.service;

import com.dw.artgallery.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    @Autowired
    NoticeRepository noticeRepository;
}
