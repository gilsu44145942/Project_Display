package com.dw.artgallery.repository;

import com.dw.artgallery.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByNoticetitleContaining(String title);
}
