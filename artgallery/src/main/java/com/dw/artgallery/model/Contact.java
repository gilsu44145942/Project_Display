package com.dw.artgallery.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "message", length = 500)
    private String message;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "status")
    private String status; // 처리 상태 (예: "대기중", "처리중", "완료")

    @Column(name = "response")
    private String response; // 관리자 답변

    @Column(name = "is_member")
    private Boolean isMember; // 회원 여부

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 회원인 경우 사용자 정보
}