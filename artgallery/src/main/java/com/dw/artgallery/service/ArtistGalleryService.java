package com.dw.artgallery.service;

import com.dw.artgallery.DTO.ArtistGalleryDTO;
import com.dw.artgallery.DTO.ArtistGalleryDetailDTO;
import com.dw.artgallery.model.ArtistGallery;
import com.dw.artgallery.repository.ArtistGalleryRepository;
import com.dw.artgallery.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ArtistGalleryDTO> getTitleArtistGallery(String title) {
        String keyword = "%" + title + "%";
        List<ArtistGallery> result = artistGalleryRepository.findByTitleLike(keyword);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("해당 제목의 전시회를 찾을 수 없습니다.");
        }
        return result.stream().map(ArtistGallery::toDto).collect(Collectors.toList());
    }

    public List<ArtistGalleryDTO> getNowArtistGallery() {
        LocalDate today = LocalDate.now();
         return artistGalleryRepository.findNowGallery(today).stream().map(ArtistGallery::toDto).toList();
    }

    public List<ArtistGalleryDTO> getPastArtistGallery() {
        LocalDate today = LocalDate.now();
        return artistGalleryRepository.findPastGallery(today).stream().map(ArtistGallery::toDto).toList();
    }

    public List<ArtistGalleryDTO> getExpectedArtistGallery() {
        LocalDate today = LocalDate.now();
        return artistGalleryRepository.findExpectedGallery(today).stream().map(ArtistGallery::toDto).toList();
    }

}
