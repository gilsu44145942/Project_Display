package com.dw.artgallery.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "message", length = 500)
    private String message;
}