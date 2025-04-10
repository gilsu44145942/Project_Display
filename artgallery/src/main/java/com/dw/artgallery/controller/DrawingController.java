package com.dw.artgallery.controller;


import com.dw.artgallery.DTO.ArtistGalleryDTO;
import com.dw.artgallery.DTO.DrawingAddDTO;
import com.dw.artgallery.DTO.DrawingDTO;
import com.dw.artgallery.DTO.DrawingUpdateDTO;
import com.dw.artgallery.model.Drawing;
import com.dw.artgallery.model.User;
import com.dw.artgallery.service.DrawingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drawing")
public class DrawingController {
    @Autowired
    DrawingService drawingService;

    // 로그인한 회원의 Drawing 조회
    @GetMapping
    public ResponseEntity<List<DrawingDTO>> getAllDrawing (@AuthenticationPrincipal User user){
        return new ResponseEntity<>(drawingService.getAllDrawing(user), HttpStatus.OK);
    }

    // Drawing 추가 기능
    @PostMapping("/add")
    public ResponseEntity<DrawingDTO> addDrawing(@RequestBody DrawingAddDTO dto,
                                                 @AuthenticationPrincipal User user) {
        Drawing created = drawingService.addDrawing(dto, user);
        return new ResponseEntity<>(created.toDto(), HttpStatus.CREATED);
    }

    // Drawing id로 수정
    @PutMapping("/update/{drawingId}")
    public ResponseEntity<DrawingDTO> updateDrawing(@PathVariable Long drawingId,
                                                    @RequestBody DrawingUpdateDTO dto,
                                                    @AuthenticationPrincipal User user) {
        Drawing updated = drawingService.updateDrawing(drawingId, dto, user);
        return new ResponseEntity<>(updated.toDto(), HttpStatus.OK);
    }

    // Drawing id로 논리적 삭제
    @PostMapping("/delete/{drawingId}")
    public ResponseEntity<String> deleteDrawing(@PathVariable Long drawingId,
                                                @AuthenticationPrincipal User user) {
        drawingService.deleteDrawing(drawingId, user);
        return new ResponseEntity<>("삭제가 완료되었습니다.", HttpStatus.OK);
    }




}
