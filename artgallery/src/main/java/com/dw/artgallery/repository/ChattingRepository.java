package com.dw.artgallery.repository;

import com.dw.artgallery.model.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting,Long> {
}
