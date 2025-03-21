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
@Table(name="전시예약")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="count")
    private int count; //예약한 인원 수 회원인 경우롸 별개로 유저가 여러명의 티켓을 발급할수 있도록

    @Column(name="sum")
    private double sum;


    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    @ManyToOne
    @JoinColumn(name = "artistGallery_id")
    private ArtistGallery artistGallery; // artistGallery 에서 가격을 가져와 인원수와 가격을 합쳐 총 금액을 합산

    @Column(name = "reservation_date",nullable = false)
    private LocalDate reservationDate;

    @OneToMany
    @JoinColumn(name = "reservation")
    private List<Ticket> ticketList = new ArrayList<>();

}

