package com.dw.artgallery.service;

import com.dw.artgallery.repository.BiographyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiographyService {
    @Autowired
    BiographyRepository biographyRepository;
}
