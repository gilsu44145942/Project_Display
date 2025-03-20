package com.dw.artgallery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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


}
