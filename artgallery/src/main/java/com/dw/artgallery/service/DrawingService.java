package com.dw.artgallery.service;


import com.dw.artgallery.DTO.DrawingDTO;
import com.dw.artgallery.DTO.DrawingUpdateDTO;
import com.dw.artgallery.model.Drawing;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.DrawingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrawingService {
    @Autowired
    DrawingRepository drawingRepository;

    public List<DrawingDTO> getAllDrawing (User user){
        return drawingRepository.findByUserAndIsDeletedFalse(user).stream().map(Drawing::toDto).collect(Collectors.toList());
    }

    public Drawing updateDrawing(Long drawingId, DrawingUpdateDTO dto, User user) {
        Drawing drawing = drawingRepository.findById(drawingId)
                .orElseThrow(() -> new RuntimeException("해당 드로잉이 존재하지 않습니다."));
        if (!drawing.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("본인의 드로잉만 수정할 수 있습니다.");
        }
        if (dto.getTitle() != null) drawing.setTitle(dto.getTitle());
        if (dto.getDescription() != null) drawing.setDescription(dto.getDescription());
        if (dto.getImgUrl() != null) drawing.setImgUrl(dto.getImgUrl());
        if (dto.getIsComplete() != null) {
            drawing.setIsComplete(dto.getIsComplete());
            if (dto.getIsComplete()) {
                drawing.setCompletionDate(LocalDate.now());
            }
        }
        return drawingRepository.save(drawing);
    }
}
