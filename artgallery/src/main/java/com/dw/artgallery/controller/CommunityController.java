package com.dw.artgallery.controller;


import com.dw.artgallery.DTO.CommunityAddDTO;
import com.dw.artgallery.DTO.CommunityDTO;
import com.dw.artgallery.DTO.CommunityDetailDTO;

import com.dw.artgallery.DTO.CommunityUpdateDTO;
import com.dw.artgallery.model.Community;
import com.dw.artgallery.model.User;
import com.dw.artgallery.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CommunityDTO> getIdCommunity(@PathVariable Long id){
        return new ResponseEntity<>(communityService.getIdCommunity(id),HttpStatus.OK);
    }

    @GetMapping("/detail/id/{id}")
    public ResponseEntity<CommunityDetailDTO> getIdCommunities(@PathVariable Long id) {
        return new ResponseEntity<>(communityService.getIdCommunities(id), HttpStatus.OK);
    }

    @GetMapping("/userid/{userid}")
    public ResponseEntity<List<CommunityDTO>> getUserIDCommunity(@PathVariable String userid) {
        return new ResponseEntity<>(communityService.getUserIDCommunity(userid), HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<List<CommunityDTO>> getMyCommunity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Object principal = authentication.getPrincipal();
        String userId;
        if (principal instanceof com.dw.artgallery.model.User user) {
            userId = user.getUserId();
        } else if (principal instanceof String id) {
            userId = id;
        } else {
            throw new IllegalStateException("알 수 없는 사용자 타입: " + principal.getClass());
        }
        List<CommunityDTO> communities = communityService.getUserIDCommunity(userId);
        return new ResponseEntity<>(communities, HttpStatus.OK);
    }


    @PostMapping("/like/{id}")
    public ResponseEntity<String> toggleLike(@PathVariable Long id,
                                             @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(communityService.toggleLike(id, user), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CommunityDTO> addCommunity(@RequestBody CommunityAddDTO dto,
                                                        @AuthenticationPrincipal User user) {
        Community created = communityService.addCommunity(dto, user);
        return new ResponseEntity<>(created.toDto(), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCommunity(@PathVariable Long id,
                                                  @RequestBody CommunityUpdateDTO updateDTO,
                                                  @AuthenticationPrincipal  User user) {
        return new ResponseEntity<>(communityService.updateCommunity(id, user, updateDTO), HttpStatus.OK);
    }


    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteCommunity(@PathVariable Long id,
                                                  @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(communityService.deleteCommunity(id, user), HttpStatus.OK);
    }


}