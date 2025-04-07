package com.dw.artgallery.service;

import com.dw.artgallery.repository.ArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtService {
    @Autowired
    ArtRepository artRepository;
}
