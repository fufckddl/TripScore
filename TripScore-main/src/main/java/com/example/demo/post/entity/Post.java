package com.example.demo.post.entity;

import com.example.demo.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address; //숙소주소

    @Column(nullable = false)
    private String title; //숙소이름

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; //숙소 소개글

    @Column(nullable = false)
    private double avgEvaluate = 0.0;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private SiteUser siteUser;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
