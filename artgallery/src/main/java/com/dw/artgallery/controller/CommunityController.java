package com.dw.artgallery.controller;


import com.dw.artgallery.DTO.CommunityDTO;
import com.dw.artgallery.DTO.CommunityDetailDTO;

import com.dw.artgallery.DTO.CommunityUpdateDTO;
import com.dw.artgallery.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

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
    public ResponseEntity<CommunityDetailDTO> getIdCommunity(@PathVariable Long id) {
        return new ResponseEntity<>(communityService.getIdCommunity(id), HttpStatus.OK);
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

    @PostMapping("/{id}/like")
    public ResponseEntity<?> toggleLike(@PathVariable Long id, Authentication authentication) {
        String userId = authentication.getName();
        communityService.toggleLike(id, userId);
        return ResponseEntity.ok("좋아요 토글 완료!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCommunity(
            @PathVariable Long id,
            @RequestBody CommunityUpdateDTO updateDTO,
            Authentication authentication) {

        String userId = authentication.getName();
        communityService.updateCommunity(id, userId, updateDTO);
        return ResponseEntity.ok("커뮤니티 글이 수정되었습니다.");
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<String> deleteCommunity(
            @PathVariable Long id,
            Authentication authentication) {

        String userId = authentication.getName();
        communityService.deleteCommunity(id, userId);
        return ResponseEntity.ok("커뮤니티 글이 삭제 처리되었습니다.");
    }





}