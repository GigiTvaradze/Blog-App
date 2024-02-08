package com.MyFirstJavaProject.blogging_app.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blog_likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }
}
