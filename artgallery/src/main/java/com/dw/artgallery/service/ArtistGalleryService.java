package com.dw.artgallery.service;

import com.dw.artgallery.DTO.ArtistGalleryDTO;
import com.dw.artgallery.DTO.ArtistGalleryDetailDTO;
import com.dw.artgallery.model.ArtistGallery;
import com.dw.artgallery.repository.ArtistGalleryRepository;
import com.dw.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistGalleryService {
    @Autowired
    ArtistGalleryRepository artistGalleryRepository;

    public List<ArtistGalleryDTO> getAllArtistGallery () {
        return artistGalleryRepository.findAll().stream().map(ArtistGallery::toDto).toList();

    }

    public ArtistGalleryDetailDTO getIdArtistGallery(Long id) {
        return artistGalleryRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("해당 ID를 가진 ArtistGallery 가 존재하지 않습니다.")).TODTO();
    }


}
