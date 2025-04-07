package com.dw.artgallery.repository;

import com.dw.artgallery.model.UserGallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGalleryRepository extends JpaRepository<UserGallery,Long> {
}
