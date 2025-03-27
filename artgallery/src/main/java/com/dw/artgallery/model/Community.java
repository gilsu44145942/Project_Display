package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "커뮤니티")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;

    @Column(name="modify_date",nullable = false)
    private LocalDateTime modifyDate;

    @ManyToMany
    @JoinTable(name = "커뮤니티_드로잉",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "drawing_id"))
    private List<Drawing> drawingList = new ArrayList<>();


}
