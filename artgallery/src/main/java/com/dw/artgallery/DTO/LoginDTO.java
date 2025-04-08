package com.dw.artgallery.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    private String userId;
    private String password;

    // 🔥 확장을 고려하여 rememberMe 추가 (선택 사항)
    private boolean rememberMe;

    public LoginDTO(String userId, String password, boolean rememberMe) {
        this.userId = userId;
        this.password = password;
        this.rememberMe = rememberMe;
    }
}
