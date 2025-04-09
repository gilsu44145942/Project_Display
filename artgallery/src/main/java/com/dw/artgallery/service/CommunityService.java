package com.dw.artgallery.service;




import com.dw.artgallery.DTO.CommunityDTO;
import com.dw.artgallery.DTO.CommunityDetailDTO;

import com.dw.artgallery.DTO.CommunityUpdateDTO;
import com.dw.artgallery.model.Community;
import com.dw.artgallery.model.CommunityLike;
import com.dw.artgallery.model.Drawing;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.CommunityLikeRepository;
import com.dw.artgallery.repository.CommunityRepository;
import com.dw.artgallery.repository.DrawingRepository;
import com.dw.artgallery.repository.UserRepository;
import com.dw.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunityService {
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommunityLikeRepository communityLikeRepository;
    @Autowired
    DrawingRepository drawingRepository;

    public List<CommunityDTO> getAllCommunity() {
        return communityRepository.findAllByIsDeletedFalse().stream()
                .map(Community::toDto)
                .toList();
    }

    public CommunityDetailDTO getIdCommunity(Long id) {
        return communityRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않거나 삭제된 커뮤니티입니다."))
                .ToDto();
    }

    public List<CommunityDTO> getUserIDCommunity(String userId) {
        List<Community> communities = communityRepository.findByUser_UserIdAndIsDeletedFalse(userId);
        if (communities.isEmpty()) {
            throw new ResourceNotFoundException("해당 유저의 커뮤니티가 없습니다.");
        }
        return communities.stream().map(Community::toDto).toList();
    }

    public void toggleLike(Long communityId, String userId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 커뮤니티 글이 없습니다."));

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Optional<CommunityLike> likeOptional = communityLikeRepository.findByUserAndCommunity(user, community);

        if (likeOptional.isPresent()) {
            communityLikeRepository.delete(likeOptional.get());
            community.setLikes(Math.max(0, community.getLikes() - 1));
        } else {
            CommunityLike newLike = CommunityLike.builder()
                    .user(user)
                    .community(community)
                    .build();
            communityLikeRepository.save(newLike);
            community.setLikes(community.getLikes() + 1);
        }

        communityRepository.save(community);
    }

    public void updateCommunity(Long communityId, String userId, CommunityUpdateDTO dto) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 커뮤니티 글이 존재하지 않습니다."));
        if (!community.getUser().getUserId().equals(userId)) {
            throw new SecurityException("본인의 글만 수정할 수 있습니다.");
        }
        community.setText(dto.getText());
        if (dto.getDrawingIds() != null) {
            List<Drawing> drawings = drawingRepository.findAllById(dto.getDrawingIds());
            community.setDrawingList(drawings);
        }
        community.setModifyDate(LocalDateTime.now());

        communityRepository.save(community);
    }


    public void deleteCommunity(Long communityId, String userId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 커뮤니티 글이 존재하지 않습니다."));
        if (!community.getUser().getUserId().equals(userId)) {
            throw new SecurityException("본인 글만 삭제할 수 있습니다.");
        }

        community.setIsDeleted(true);
        communityRepository.save(community);
    }
}



