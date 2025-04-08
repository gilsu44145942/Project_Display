//package com.dw.artgallery.controller;
//
//import com.dw.artgallery.DTO.DrawingDTO;
//import com.dw.artgallery.service.DrawingService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/drawings")
//@RequiredArgsConstructor
//public class DrawingController {
//    private final DrawingService drawingService;
//
//    // 드로잉 생성
//    @PostMapping
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<DrawingDTO> createDrawing(@RequestBody DrawingDTO drawingDTO) {
//        return new ResponseEntity<>(drawingService.createDrawing(drawingDTO), HttpStatus.CREATED);
//    }
//
//    // 드로잉 조회
//    @GetMapping("/{id}")
//    public ResponseEntity<DrawingDTO> getDrawing(@PathVariable Long id) {
//        return ResponseEntity.ok(drawingService.getDrawing(id));
//    }
//
//    // 유저 갤러리의 모든 드로잉 조회
//    @GetMapping("/gallery/{userGalleryId}")
//    public ResponseEntity<List<DrawingDTO>> getDrawingsByUserGallery(@PathVariable Long userGalleryId) {
//        return ResponseEntity.ok(drawingService.getDrawingsByUserGallery(userGalleryId));
//    }
//
//    // 임시 저장된 드로잉 조회
//    @GetMapping("/temporary/{userGalleryId}")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<List<DrawingDTO>> getTemporaryDrawings(@PathVariable Long userGalleryId) {
//        return ResponseEntity.ok(drawingService.getTemporaryDrawings(userGalleryId));
//    }
//
//    // 드로잉 수정
//    @PutMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<DrawingDTO> updateDrawing(
//            @PathVariable Long id,
//            @RequestBody DrawingDTO drawingDTO) {
//        return ResponseEntity.ok(drawingService.updateDrawing(id, drawingDTO));
//    }
//
//    // 드로잉 삭제
//    @DeleteMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<Void> deleteDrawing(@PathVariable Long id) {
//        drawingService.deleteDrawing(id);
//        return ResponseEntity.ok().build();
//    }
//}