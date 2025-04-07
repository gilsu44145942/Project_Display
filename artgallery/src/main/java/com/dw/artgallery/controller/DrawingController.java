package com.dw.artgallery.controller;

import com.dw.artgallery.service.DrawingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DrawingController {
    @Autowired
    DrawingService drawingService;
}
