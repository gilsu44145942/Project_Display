package com.dw.artgallery.DTO;

import com.dw.artgallery.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserGetDTO {
    private String userId;
    private String nickName;
    private String realName;
    private String email;
    private LocalDate birthday;
    private String address;
    private LocalDate enrolmentDate;
    private double point;
    private Gender gender;
}
