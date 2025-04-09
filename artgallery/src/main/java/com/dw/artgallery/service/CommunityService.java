package com.dw.artgallery.service;



import com.dw.artgallery.DTO.ArtistGalleryDetailDTO;
import com.dw.artgallery.DTO.CommunityDTO;
import com.dw.artgallery.DTO.CommunityDetailDTO;
import com.dw.artgallery.model.ArtistGallery;
import com.dw.artgallery.model.Community;
import com.dw.artgallery.repository.CommunityRepository;
import com.dw.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunityService {
    @Autowired
    CommunityRepository communityRepository;

    public List<CommunityDTO> getAllCommunity () {
        return communityRepository.findAll().stream().map(Community::toDto).toList();

    }

    public CommunityDetailDTO getIdCommunity(Long id) {
        return communityRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("해당 ID를 가진 Community 가 존재하지 않습니다.")).ToDto();
    }

    public List<CommunityDTO> getUserIDCommunity(String userId) {
        List<Community> communities = communityRepository.findByUser_UserId(userId);
        if (communities.isEmpty()) {
            throw new ResourceNotFoundException("userId [" + userId + "] 에 대한 Community 를 찾을 수 없습니다.");
        }
        return communities.stream()
                .map(Community::toDto)
                .collect(Collectors.toList());
    }
}
