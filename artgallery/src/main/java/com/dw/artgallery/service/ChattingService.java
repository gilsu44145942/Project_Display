package com.dw.artgallery.service;

import com.dw.artgallery.repository.ChattingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChattingService {
    @Autowired
    ChattingRepository chattingRepository;
}
