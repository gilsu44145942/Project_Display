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
@Table(name="커뮤니티")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="text")
    private String text;

    @ManyToMany
    @JoinTable(name = "커뮤니티_드로잉",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "drawing_id"))
    private List<Drawing> drawingList = new ArrayList<>();

    @OneToMany(mappedBy="community")
    private List<Comment> commentList = new ArrayList<>();
}
