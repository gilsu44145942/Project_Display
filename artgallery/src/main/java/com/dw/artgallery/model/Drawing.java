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
@Table(name="드로잉")
public class Drawing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="img_url")
    private String imgUrl;

    @Column(name="title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name="completion_date")
    private LocalDate completionDate;

    @Column(name = "is_complete")
    private boolean isComplete;

    @ManyToMany(mappedBy = "drawingList")
    private List<Community> communityList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="usergallery_id")
    private UserGallery userGallery;
}
