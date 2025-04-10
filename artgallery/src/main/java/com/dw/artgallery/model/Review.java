package com.dw.artgallery.model;

import com.dw.artgallery.DTO.ReviewDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false, length = 200)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(name = "created_at") // 작성일자
    private LocalDate createdAt;

    public ReviewDTO toDTO() {
        return new ReviewDTO(
                this.getId(),
                this.getContent(),
                this.getCreatedAt(),
                this.getUser().getUsername(),
                this.getGoods().getId()
        );
    }
}
