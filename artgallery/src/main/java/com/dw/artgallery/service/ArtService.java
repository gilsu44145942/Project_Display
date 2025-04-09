package com.dw.artgallery.service;

import com.dw.artgallery.DTO.ArtDTO;
import com.dw.artgallery.DTO.ArtUpdateDTO;
import com.dw.artgallery.model.Art;
import com.dw.artgallery.model.Artist;
import com.dw.artgallery.repository.ArtRepository;
import com.dw.artgallery.repository.ArtistRepository;
import com.dw.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtService {
    @Autowired
    ArtRepository artRepository;
    @Autowired
    ArtistRepository artistRepository;

    public List<Art> getAllArt() {
        return artRepository.findAll();
    }

    // ID로 작품 조회 후 DTO 변환
    public ArtDTO findByIdArtId(Long id) {
        Art art = artRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Art not found with id: " + id));

        return convertToDTO(art);
    }

    // 수정 (UPDATE)
    public ArtDTO updateArt(Long id, ArtUpdateDTO artUpdateDTO) {
        Art art = artRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Art not found with id: " + id));

        // 변경할 값 적용
        art.setTitle(artUpdateDTO.getTitle());
        art.setImgUrl(artUpdateDTO.getImgUrl());
        art.setDescription(artUpdateDTO.getDescription());
        art.setCompletionDate(artUpdateDTO.getCompletionDate());
        art.setUploadDate(artUpdateDTO.getUploadDate());

        // 작가 변경이 있을 경우
        if (artUpdateDTO.getArtistId() != null) {
            Artist artist = artistRepository.findById(artUpdateDTO.getArtistId())
                    .orElseThrow(() -> new ResourceNotFoundException("Artist not found with id: " + artUpdateDTO.getArtistId()));
            art.setArtist(artist);
        }

        // 수정된 데이터 저장
        Art updatedArt = artRepository.save(art);
        return convertToDTO(updatedArt);
    }






    // 순환참조 방지 DTO
    private ArtDTO convertToDTO(Art art) {
        return new ArtDTO(
                art.getId(),
                art.getTitle(),
                art.getImgUrl(),
                art.getDescription(),
                art.getCompletionDate(),
                art.getUploadDate(),
                art.getArtist() != null ? art.getArtist().getName() : "Unknown"
        );
    }
}
