package com.MyFirstJavaProject.blogging_app.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blog_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
