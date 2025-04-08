package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.ArtistGalleryDTO;
import com.dw.artgallery.DTO.ArtistGalleryDetailDTO;
import com.dw.artgallery.DTO.UserGalleryDTO;
import com.dw.artgallery.DTO.UserGalleryDetailDTO;
import com.dw.artgallery.service.UserGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usergallery")
public class UserGalleryController {
    @Autowired
    UserGalleryService userGalleryService;

    @GetMapping
    public ResponseEntity<List<UserGalleryDTO>> getAllUserGallery (){
        return new ResponseEntity<>(userGalleryService.getAllUserGallery(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserGalleryDetailDTO> getIdUserGallery (@PathVariable Long id){
        return new ResponseEntity<>(userGalleryService.getIdUserGallery(id), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<UserGalleryDTO>> getTitleUserGallery (@PathVariable String title) {
        return new ResponseEntity<>(userGalleryService.getTitleUserGallery(title),HttpStatus.OK);
    }

    @GetMapping("/now")
    public ResponseEntity<List<UserGalleryDTO>> getNowUserGallery () {
        return new ResponseEntity<>(userGalleryService.getNowUserGallery(), HttpStatus.OK);
    }

    @GetMapping("/past")
    public ResponseEntity<List<UserGalleryDTO>> getPastUSerGallery () {
        return new ResponseEntity<>(userGalleryService.getPastUSerGallery(), HttpStatus.OK);
    }

    @GetMapping("/expected")
    public ResponseEntity<List<UserGalleryDTO>> getExpectedUserGallery () {
        return new ResponseEntity<>(userGalleryService.getExpectedUserGallery(), HttpStatus.OK);
    }
}
