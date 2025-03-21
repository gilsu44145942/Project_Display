package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="userGallery")
public class UserGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name="poster_url",nullable = false)
    private String posterUrl;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(name = "userGallery_user",
            joinColumns = @JoinColumn(name = "userGallery_id"),
            inverseJoinColumns = @JoinColumn(name = "user_name"))
    private List<User> userList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "drawing_id")
    private List<Drawing> drawingList = new ArrayList<>();
}
