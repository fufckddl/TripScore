package com.example.demo.user.entity;


import com.example.demo.user.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private int point = 0;

    @Column(nullable = false)
    private double avgEvaluate = 0.0;

    //ROLE
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;


    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updateAt;

    @PrePersist //자동실행 어로케이션
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }
}
