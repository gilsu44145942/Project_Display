package com.dw.artgallery.repository;

import com.dw.artgallery.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
    public List<Artist> findByNameLike(String name);
}
