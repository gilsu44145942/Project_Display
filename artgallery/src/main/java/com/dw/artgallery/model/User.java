package com.dw.artgallery.model;

import com.dw.artgallery.DTO.UserDTO;
import com.dw.artgallery.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name="user_id",nullable = false, unique = true)
    private String userId;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickName;

    @Column(name = "real_name", nullable = false)
    private String realName;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "enrolment_date")
    private LocalDate enrolmentDate = LocalDate.now(); // 기본값 설정

    @Column(name = "point")
    private double point = 0.0; // 기본 포인트 설정

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority;



    public User(String userId, String encode, String nickName, String email, String realName, LocalDate birthday, String address, Gender gender, Authority authority) {
    }

    public UserDTO toDTO() {
        return new UserDTO(
                this.userId,
                null,
                this.nickName,
                this.realName,
                this.email,
                this.birthday,
                this.address,
                this.enrolmentDate,
                this.point,
                this.gender
        );
    }



}




