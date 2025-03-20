package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="전시예약")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "display_title")
    private Display display;

    @Column(name ="start_date")
    private LocalDate start_date;

    @Column(name ="end_date")
    private LocalDate end_date;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;
}
