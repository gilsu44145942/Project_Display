package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.ArtDTO;
import com.dw.artgallery.DTO.ArtDetailDTO;
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


    @GetMapping("/{id}/arts")
    public ResponseEntity<List<ArtDTO>> getArtByArtistId(@PathVariable Long id) {
        return new ResponseEntity<>(artistService.getArtByArtistId(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/art")
    public ResponseEntity<ArtDetailDTO> addArtToArtist(@PathVariable Long id, @RequestBody ArtDetailDTO artDTO) {
        ArtDetailDTO saved = artistService.addArtForArtist(id, artDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
