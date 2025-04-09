package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.ArtDTO;
import com.dw.artgallery.DTO.ArtDetailDTO;
import com.dw.artgallery.DTO.ArtUpdateDTO;
import com.dw.artgallery.model.Art;
import com.dw.artgallery.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/art")
public class ArtController {
    @Autowired
    ArtService artService;

    // 모든 작품 조회
    @GetMapping("")
    public ResponseEntity<List<Art>> getAllArt() {
        return ResponseEntity.ok(artService.getAllArt());
    }

    // 작품 ID로 조회
    @GetMapping("/id/{id}")
    public ResponseEntity<ArtDTO> getIdArt(@PathVariable Long id) {
        return ResponseEntity.ok(artService.findByIdArtId(id));
    }


    // 작품 수정 (UPDATE) 관리자만 가능
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArtDTO> updateArt(@PathVariable Long id, @RequestBody ArtUpdateDTO artUpdateDTO) {
        return ResponseEntity.ok(artService.updateArt(id, artUpdateDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteArt(@PathVariable Long id) {
        artService.deleteArtById(id);
        return ResponseEntity.ok("작품이 성공적으로 삭제되었습니다.");
    }
}
