package com.dw.artgallery.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortOrder {
    ASC("오름차순"),
    DESC("내림차순");

    private final String sortOrder;

}
