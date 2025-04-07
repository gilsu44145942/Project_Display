package com.dw.artgallery.controller;

import com.dw.artgallery.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/art")
public class ArtController {
    @Autowired
    ArtService artService;
}
