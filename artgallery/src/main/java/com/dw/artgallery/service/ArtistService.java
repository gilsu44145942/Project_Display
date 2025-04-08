package com.dw.artgallery.service;

import com.dw.artgallery.DTO.ArtistDTO;
import com.dw.artgallery.model.Artist;
import com.dw.artgallery.repository.ArtistRepository;
import com.dw.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArtistService {
    ArtistRepository artistRepository;

    public List<ArtistDTO> getAllArtist(){
        return artistRepository.findAll().stream().map(ArtistDTO::fromEntity).toList();
    }

    public ArtistDTO getArtistById(Long id){
        return artistRepository.findById(id)
                .map(ArtistDTO::fromEntity)
                .orElseThrow(()-> new ResourceNotFoundException("해당 작가/화가가 존재하지 않습니다."));
    }

    public List<ArtistDTO> getArtistByName(String name){
        return artistRepository
                .findByNameLike("%"+name+"%")
                .stream().map(ArtistDTO::fromEntity).toList();
    }

    public ArtistDTO addArtist(ArtistDTO artistDTO){
        Artist artist = new Artist();

        artist.setName(artistDTO.getName());
        artist.setProfile_img(artistDTO.getProfile_img());
    }
}
