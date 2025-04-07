package com.dw.artgallery.repository;

import com.dw.artgallery.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community,Long> {
}
