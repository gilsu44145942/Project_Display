package com.dw.artgallery.controller;

import com.dw.artgallery.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArtController {
    @Autowired
    ArtService artService;
}
