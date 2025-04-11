package com.dw.artgallery.service;

import com.dw.artgallery.DTO.UserGalleryDTO;
import com.dw.artgallery.DTO.UserGalleryDetailDTO;
import com.dw.artgallery.model.UserGallery;
import com.dw.artgallery.repository.UserGalleryRepository;
import com.dw.artgallery.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGalleryService {
    @Autowired
    UserGalleryRepository userGalleryRepository;

    public List<UserGalleryDTO> getAllUserGallery () {
        return userGalleryRepository.findAll().stream().map(UserGallery::ToDTO).toList();

    }

    public UserGalleryDetailDTO getIdUserGallery(Long id) {
        return userGalleryRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("해당 ID를 가진 ArtistGallery 가 존재하지 않습니다.")).toDto();
    }

    public List<UserGalleryDTO> getTitleUserGallery(String title) {
        String keyword = "%" + title + "%";
        List<UserGallery> result = userGalleryRepository.findByTitleLike(keyword);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("해당 제목의 전시회를 찾을 수 없습니다.");
        }
        return result.stream()
                .map(UserGallery::ToDTO)
                .collect(Collectors.toList());
    }

    public List<UserGalleryDTO> getNowUserGallery() {
        LocalDate today = LocalDate.now();
        return userGalleryRepository.findNowGallery(today).stream().map(UserGallery::ToDTO).toList();
    }

    public List<UserGalleryDTO> getPastUSerGallery() {
        LocalDate today = LocalDate.now();
        return userGalleryRepository.findPastGallery(today).stream().map(UserGallery::ToDTO).toList();
    }

    public List<UserGalleryDTO> getExpectedUserGallery() {
        LocalDate today = LocalDate.now();
        return userGalleryRepository.findExpectedGallery(today).stream().map(UserGallery::ToDTO).toList();
    }
}
