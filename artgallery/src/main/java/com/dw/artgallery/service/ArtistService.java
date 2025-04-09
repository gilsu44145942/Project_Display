package com.dw.artgallery.service;

import com.dw.artgallery.DTO.ArtDetailDTO;
import com.dw.artgallery.DTO.ArtistDTO;
import com.dw.artgallery.model.Art;
import com.dw.artgallery.model.Artist;
import com.dw.artgallery.repository.ArtRepository;
import com.dw.artgallery.repository.ArtistRepository;
import com.dw.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtRepository artRepository;

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

        List<Art> artList = artistDTO.getArtDetailDTOList()
                .stream().map(artDetailDTO -> artDetailDTO.toEntity(artist))
                .toList();
        artist.setArtList(artList);

        Artist savedArtist = artistRepository.save(artist);
        return ArtistDTO.fromEntity(savedArtist);
    }

    @Transactional
    public ArtistDTO updateArtist(Long id, ArtistDTO artistDTO){
        Artist artist =
    }
}
