package com.dw.artgallery.service;

import com.dw.artgallery.repository.UserGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGalleryService {
    @Autowired
    UserGalleryRepository userGalleryRepository;
}
