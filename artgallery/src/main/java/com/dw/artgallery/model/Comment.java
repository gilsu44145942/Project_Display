package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="답글")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="text",nullable = false)
    private String text;

    @Column(name="creation_date",nullable = false)
    private LocalDateTime creationDate;



    @ManyToOne
    @JoinColumn(name="user_name")
    private User userName;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community community;

}

