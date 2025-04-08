package com.dw.artgallery.service;



import com.dw.artgallery.DTO.CommunityDTO;
import com.dw.artgallery.DTO.CommunityDetailDTO;
import com.dw.artgallery.model.Community;
import com.dw.artgallery.repository.CommunityRepository;
import com.dw.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
