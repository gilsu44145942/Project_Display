package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.ArtistGalleryDTO;
import com.dw.artgallery.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {
    @Autowired
    CommunityService communityService;


}
