package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.ArtistGalleryDTO;
import com.dw.artgallery.DTO.ArtistGalleryDetailDTO;
import com.dw.artgallery.service.ArtistGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/artistgallery")
public class ArtistGalleryController {
    @Autowired
    ArtistGalleryService artistGalleryService;

    @GetMapping
    public ResponseEntity<List<ArtistGalleryDTO>> getAllArtistGallery (){
        return new ResponseEntity<>(artistGalleryService.getAllArtistGallery(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ArtistGalleryDetailDTO> getIdArtistGallery (@PathVariable Long id){
        return new ResponseEntity<>(artistGalleryService.getIdArtistGallery(id), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ArtistGalleryDTO>> getTitleArtistGallery (@PathVariable String title) {
        return new ResponseEntity<>(artistGalleryService.getTitleArtistGallery(title),HttpStatus.OK);
    }

    @GetMapping("/now")
    public ResponseEntity<List<ArtistGalleryDTO>> getNowArtistGallery () {
        return new ResponseEntity<>(artistGalleryService.getNowArtistGallery(), HttpStatus.OK);
    }

    @GetMapping("/past")
    public ResponseEntity<List<ArtistGalleryDTO>> getPastArtistGallery () {
        return new ResponseEntity<>(artistGalleryService.getPastArtistGallery(), HttpStatus.OK);
    }

    @GetMapping("/expected")
    public ResponseEntity<List<ArtistGalleryDTO>> getExpectedArtistGallery () {
        return new ResponseEntity<>(artistGalleryService.getExpectedArtistGallery(), HttpStatus.OK);
    }

}
