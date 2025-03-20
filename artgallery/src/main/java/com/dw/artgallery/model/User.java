package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="사용자")
public class User {

    @Id
    @Column(name="user_name",nullable = false, unique = true)
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="birthday")
    private LocalDate birthday;

    @Column(name="point")
    private double point;

    @Column(name="gender")
    private String gender;

    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority;



}
