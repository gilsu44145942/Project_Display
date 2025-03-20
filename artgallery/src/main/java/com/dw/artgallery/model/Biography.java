package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="수상경력")
public class Biography {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
