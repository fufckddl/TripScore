package com.example.demo.image.userimg;

import com.example.demo.post.entitiy.Post;
import com.example.demo.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url; //이미지 주소

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiteUser siteUser;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post p;
}
