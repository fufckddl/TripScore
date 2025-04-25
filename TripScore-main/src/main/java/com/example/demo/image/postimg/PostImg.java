package com.example.demo.image.postimg;


import com.example.demo.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PostImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url; //이미지 url

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiteUser siteUser;
}
