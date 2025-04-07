package com.dw.artgallery.service;

import com.dw.artgallery.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    @Autowired
    ArtistRepository artistRepository;
}
