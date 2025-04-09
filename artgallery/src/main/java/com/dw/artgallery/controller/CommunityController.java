package com.dw.artgallery.controller;


import com.dw.artgallery.DTO.CommunityDTO;
import com.dw.artgallery.DTO.CommunityDetailDTO;
import com.dw.artgallery.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/community")
public class CommunityController {
    @Autowired
    CommunityService communityService;

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> getAllCommunity() {
        return new ResponseEntity<>(communityService.getAllCommunity(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CommunityDetailDTO> getIdCommunity (@PathVariable Long id){
        return new ResponseEntity<>(communityService.getIdCommunity(id),HttpStatus.OK);
    }

    @GetMapping("/userid/{userid}")
    public ResponseEntity<List<CommunityDTO>> getUserIDCommunity (@PathVariable String userid){
        return new ResponseEntity<>(communityService.getUserIDCommunity(userid),HttpStatus.OK);
    }




}
