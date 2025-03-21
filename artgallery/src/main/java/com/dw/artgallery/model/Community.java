package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name="like")
    private int like;

    @OneToMany(mappedBy = "community")
    private List<Comment> commentList = new ArrayList<>();

//    @ManyToMany(mappedBy = "communityList")
    @Column(name = "drawingList")
    private List<Drawing> drawingList = new ArrayList<>();



}
