package com.dw.artgallery.repository;

import com.dw.artgallery.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community,Long> {
    List<Community> findByUser_UserId(String userid);
    List<Community> findAllByIsDeletedFalse();

    List<Community> findByUser_UserIdAndIsDeletedFalse(String userId);

    Optional<Community> findByIdAndIsDeletedFalse(Long id);
}
