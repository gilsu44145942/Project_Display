package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.ArtDTO;
import com.dw.artgallery.DTO.ArtDetailDTO;
import com.dw.artgallery.DTO.ArtistDTO;
import com.dw.artgallery.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtist(){
        return new ResponseEntity<>(artistService.getAllArtist(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long id){
        return new ResponseEntity<>(artistService.getArtistById(id),HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ArtistDTO>> getArtistByName(@PathVariable String name){
        return new ResponseEntity<>(artistService.getArtistByName(name),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ArtistDTO> addArtist(@RequestBody ArtistDTO artistDTO){
        return new ResponseEntity<>(artistService.addArtist(artistDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long id, @RequestBody ArtistDTO artistDTO) {
        return new ResponseEntity<>(artistService.updateArtist(id, artistDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        return new ResponseEntity<>(artistService.deleteArtist(id), HttpStatus.OK);
    }

}
