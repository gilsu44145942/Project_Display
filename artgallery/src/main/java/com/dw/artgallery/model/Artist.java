package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="아티스트")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "artist")
    @Column(name = "biography")
    private List<Biography> biographyList;

    @Column(name = "profile_img")
    private String profile_img;
}
