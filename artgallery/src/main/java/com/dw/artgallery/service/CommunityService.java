package com.dw.artgallery.service;




import com.dw.artgallery.DTO.CommunityAddDTO;
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

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.awt.SystemColor.text;


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


    public CommunityDTO getIdCommunity(Long id) {
        return communityRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않거나 삭제된 커뮤니티입니다."))
                .toDto();
    }

    public CommunityDetailDTO getIdCommunities(Long id) {
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


    public String toggleLike(Long id, User user) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 커뮤니티 글이 없습니다."));

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
        return "좋아요 토글 완료!";
    }



        public Community addCommunity(CommunityAddDTO dto, User user) {
            List<Drawing> userDrawings = drawingRepository.findAllById(dto.getDrawingIds());
            for (Drawing d : userDrawings) {
                if (!d.getUser().getUserId().equals(user.getUserId())) {
                    throw new IllegalArgumentException("본인이 소유한 드로잉만 첨부할 수 있습니다.");
                }
            }
            Community community = new Community();
            community.setText(dto.getText());
            community.setDrawingList(userDrawings);
            community.setUser(user);
            community.setModifyDate(LocalDateTime.now());
            community.setIsDeleted(false);
            community.setLikes(0L);

            return communityRepository.save(community);
        }

    public String updateCommunity(Long id, User user, CommunityUpdateDTO dto) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 커뮤니티 글이 존재하지 않습니다."));

        if (!community.getUser().getUserId().equals(user.getUserId())) {
            throw new SecurityException("본인의 글만 수정할 수 있습니다.");
        }

        community.setText(dto.getText());
        if (dto.getDrawingIds() != null) {
            List<Drawing> drawings = drawingRepository.findAllById(dto.getDrawingIds());
            community.setDrawingList(drawings);
        }
        community.setModifyDate(LocalDateTime.now());

        communityRepository.save(community);
        return "커뮤니티 글이 수정되었습니다.";
    }

    public String deleteCommunity(Long id, User user) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 커뮤니티 글이 존재하지 않습니다."));

        if (!community.getUser().getUserId().equals(user.getUserId())) {
            throw new SecurityException("본인 글만 삭제할 수 있습니다.");
        }

        community.setIsDeleted(true);
        communityRepository.save(community);
        return "커뮤니티 글이 삭제 처리되었습니다.";
    }
}



