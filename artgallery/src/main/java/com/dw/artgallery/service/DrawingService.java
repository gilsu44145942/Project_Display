package com.dw.artgallery.service;

import com.dw.artgallery.repository.DrawingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrawingService {
    @Autowired
    DrawingRepository drawingRepository;
}
