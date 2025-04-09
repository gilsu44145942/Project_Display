package com.dw.artgallery.repository;

import com.dw.artgallery.model.Art;
import com.dw.artgallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtRepository extends JpaRepository<Art,Long> {

}
