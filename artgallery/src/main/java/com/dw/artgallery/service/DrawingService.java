//package com.dw.artgallery.service;
//
//import com.dw.artgallery.DTO.DrawingDTO;
//import com.dw.artgallery.model.Drawing;
//import com.dw.artgallery.model.UserGallery;
//import com.dw.artgallery.repository.DrawingRepository;
//import com.dw.artgallery.repository.UserGalleryRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class DrawingService {
//    private final DrawingRepository drawingRepository;
//    private final UserGalleryRepository userGalleryRepository;
//
//    // 드로잉 생성
//    @Transactional
//    public DrawingDTO createDrawing(DrawingDTO drawingDTO) {
//        try {
//            log.info("드로잉 생성 시도 - 제목: {}, 유저갤러리ID: {}", drawingDTO.getTitle(), drawingDTO.getUserGalleryId());
//
//            UserGallery userGallery = userGalleryRepository.findById(drawingDTO.getUserGalleryId())
//                    .orElseThrow(() -> {
//                        log.error("유저 갤러리를 찾을 수 없음 - ID: {}", drawingDTO.getUserGalleryId());
//                        return new RuntimeException("UserGallery not found");
//                    });
//
//            Drawing drawing = drawingDTO.toEntity();
//            drawing.setCreatedDate(LocalDateTime.now());
//            drawing.setLastModifiedDate(LocalDateTime.now());
//            drawing.setUserGallery(userGallery);
//
//            Drawing savedDrawing = drawingRepository.save(drawing);
//            log.info("드로잉 생성 완료 - ID: {}, 제목: {}", savedDrawing.getId(), savedDrawing.getTitle());
//            return DrawingDTO.toDTO(savedDrawing);
//        } catch (Exception e) {
//            log.error("드로잉 생성 실패 - 제목: {}, 에러: {}", drawingDTO.getTitle(), e.getMessage());
//            throw e;
//        }
//    }
//
//    // 드로잉 조회
//    public DrawingDTO getDrawing(Long id) {
//        try {
//            log.info("드로잉 조회 시도 - ID: {}", id);
//            Drawing drawing = drawingRepository.findById(id)
//                    .orElseThrow(() -> {
//                        log.error("드로잉을 찾을 수 없음 - ID: {}", id);
//                        return new RuntimeException("Drawing not found");
//                    });
//            log.info("드로잉 조회 완료 - ID: {}, 제목: {}", id, drawing.getTitle());
//            return DrawingDTO.toDTO(drawing);
//        } catch (Exception e) {
//            log.error("드로잉 조회 실패 - ID: {}, 에러: {}", id, e.getMessage());
//            throw e;
//        }
//    }
//
//    // 유저 갤러리의 모든 드로잉 조회
//    public List<DrawingDTO> getDrawingsByUserGallery(Long userGalleryId) {
//        try {
//            log.info("유저 갤러리의 드로잉 조회 시도 - UserGalleryId: {}", userGalleryId);
//            List<Drawing> drawings = drawingRepository.findByUserGalleryId(userGalleryId);
//            log.info("유저 갤러리의 드로잉 조회 완료 - UserGalleryId: {}, 조회된 드로잉 수: {}", userGalleryId, drawings.size());
//            return drawings.stream()
//                    .map(DrawingDTO::toDTO)
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            log.error("유저 갤러리의 드로잉 조회 실패 - UserGalleryId: {}, 에러: {}", userGalleryId, e.getMessage());
//            throw e;
//        }
//    }
//
//    // 임시 저장된 드로잉 조회
//    public List<DrawingDTO> getTemporaryDrawings(Long userGalleryId) {
//        try {
//            log.info("임시 저장 드로잉 조회 시도 - UserGalleryId: {}", userGalleryId);
//            List<Drawing> drawings = drawingRepository.findByUserGalleryIdAndIsTemporary(userGalleryId, true);
//            log.info("임시 저장 드로잉 조회 완료 - UserGalleryId: {}, 조회된 임시 드로잉 수: {}", userGalleryId, drawings.size());
//            return drawings.stream()
//                    .map(DrawingDTO::toDTO)
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            log.error("임시 저장 드로잉 조회 실패 - UserGalleryId: {}, 에러: {}", userGalleryId, e.getMessage());
//            throw e;
//        }
//    }
//
//    // 드로잉 수정
//    @Transactional
//    public DrawingDTO updateDrawing(Long id, DrawingDTO drawingDTO) {
//        try {
//            log.info("드로잉 수정 시도 - ID: {}, 새 제목: {}", id, drawingDTO.getTitle());
//            Drawing drawing = drawingRepository.findById(id)
//                    .orElseThrow(() -> {
//                        log.error("수정할 드로잉을 찾을 수 없음 - ID: {}", id);
//                        return new RuntimeException("Drawing not found");
//                    });
//
//            drawing.setTitle(drawingDTO.getTitle());
//            drawing.setDescription(drawingDTO.getDescription());
//            drawing.setImgUrl(drawingDTO.getImgUrl());
//            drawing.setLastModifiedDate(LocalDateTime.now());
//            drawing.setTemporary(drawingDTO.isTemporary());
//
//            Drawing updatedDrawing = drawingRepository.save(drawing);
//            log.info("드로잉 수정 완료 - ID: {}, 제목: {}", id, updatedDrawing.getTitle());
//            return DrawingDTO.toDTO(updatedDrawing);
//        } catch (Exception e) {
//            log.error("드로잉 수정 실패 - ID: {}, 에러: {}", id, e.getMessage());
//            throw e;
//        }
//    }
//
//    // 드로잉 삭제
//    @Transactional
//    public void deleteDrawing(Long id) {
//        try {
//            log.info("드로잉 삭제 시도 - ID: {}", id);
//            drawingRepository.deleteById(id);
//            log.info("드로잉 삭제 완료 - ID: {}", id);
//        } catch (Exception e) {
//            log.error("드로잉 삭제 실패 - ID: {}, 에러: {}", id, e.getMessage());
//            throw e;
//        }
//    }
//}