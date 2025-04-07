package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.ArtDTO;
import com.dw.artgallery.DTO.ArtistDTO;
import com.dw.artgallery.service.ArtistService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    @Autowired
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

    @PostMapping
    public ResponseEntity<ArtistDTO> addArtist(@RequestBody ArtistDTO artistDTO){
        return new ResponseEntity<>(artistService.addArtist(artistDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long id, @RequestBody ArtistDTO artistDTO) {
        return new ResponseEntity<>(artistService.updateArtist(id, artistDTO), HttpStatus.OK);
    }


    @GetMapping("/{id}/arts")
    public ResponseEntity<List<ArtDTO>> getArtByArtistId(@PathVariable Long id) {
        return new ResponseEntity<>(artistService.getArtByArtistId(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        return new ResponseEntity<>(artistService.deleteArtist(id), HttpStatus.OK);
    }

}
