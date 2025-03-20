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
@Table(name="사용자전시회")
public class UserGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name="poster_url")
    private String posterUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;


    @ManyToMany
    @JoinTable(name = "사용자전시회_사용자",
            joinColumns = @JoinColumn(name = "userGallery_id"),
            inverseJoinColumns = @JoinColumn(name = "user_name"))
    private List<User> userList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "사용자전시회_드로잉",
            joinColumns = @JoinColumn(name = "userGallery_id"),
            inverseJoinColumns = @JoinColumn(name = "drawing_id"))
    private List<Drawing> drawingList = new ArrayList<>();
}
