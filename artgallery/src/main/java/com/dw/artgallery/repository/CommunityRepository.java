package com.dw.artgallery.repository;

import com.dw.artgallery.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community,Long> {
    List<Community> findByUser_UserId(String userid);
}
