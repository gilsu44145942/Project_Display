package com.dw.artgallery.service;

import com.dw.artgallery.repository.ArtistGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistGalleryService {
    @Autowired
    ArtistGalleryRepository artistGalleryRepository;
}
