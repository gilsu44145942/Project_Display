package com.dw.artgallery.service;

import com.dw.artgallery.DTO.NoticeDTO;
import com.dw.artgallery.model.Notice;
import com.dw.artgallery.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private UserService userService;

    // 모든 공지 조회
    public List<NoticeDTO> getAllNotices() {
        return noticeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 공지 조회
    public NoticeDTO getNoticeById(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 공지를 찾을 수 없습니다."));
        return convertToDTO(notice);
    }

    // 제목 조회
    public List<NoticeDTO> getNoticesByTitle(String title) {
        return noticeRepository.findByNoticetitleContaining(title)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NoticeDTO saveNotice(NoticeDTO dto) {
        if (dto.getNoticetitle() == null || dto.getContent() == null) {
            throw new IllegalArgumentException("제목과 내용을 모두 작성해주세요.");
        }

        Notice notice = new Notice();
        notice.setId(dto.getId());
        notice.setNoticetitle(dto.getNoticetitle());
        notice.setContent(dto.getContent());
        notice.setCreatedDate(LocalDate.now());

        return convertToDTO(noticeRepository.save(notice));
    }

    // 삭제
    public String deleteNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 공지를 찾을 수 없습니다."));

        noticeRepository.deleteById(id);
        return "공지 ID: " + id + " 삭제 완료";
    }

    private NoticeDTO convertToDTO(Notice notice) {
        NoticeDTO dto = new NoticeDTO();
        dto.setId(notice.getId());
        dto.setNoticetitle(notice.getNoticetitle());
        dto.setContent(notice.getContent());
        return dto;
    }
}
