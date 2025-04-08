package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.NoticeDTO;
import com.dw.artgallery.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public List<NoticeDTO> getAllNotices() {
        return noticeService.getAllNotices();
    }

    @GetMapping("/{id}")
    public NoticeDTO getNoticeById(@PathVariable Long id) {
        return noticeService.getNoticeById(id);
    }

    @GetMapping("/search")
    public List<NoticeDTO> getNoticesByTitle(@RequestParam String title) {
        return noticeService.getNoticesByTitle(title);
    }

    @PostMapping
    public NoticeDTO createOrUpdateNotice(@RequestBody NoticeDTO dto, HttpServletRequest request) {
        return noticeService.saveNotice(dto, request);
    }

    @DeleteMapping("/{id}")
    public String deleteNotice(@PathVariable Long id, HttpServletRequest request) {
        return noticeService.deleteNotice(id, request);
    }
}
