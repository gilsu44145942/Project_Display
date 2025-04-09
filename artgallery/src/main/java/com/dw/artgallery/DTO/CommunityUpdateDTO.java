package com.dw.artgallery.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CommunityUpdateDTO {
    private String text;
    private List<Long> drawingIds;
}