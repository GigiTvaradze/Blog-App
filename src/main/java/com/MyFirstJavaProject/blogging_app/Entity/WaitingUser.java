package com.MyFirstJavaProject.blogging_app.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blog_waiting_users")
public class WaitingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiting_user_id")
    private Long waitingUserId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    public void setWaitingUserId(Long waitingUserId) {
        this.waitingUserId = waitingUserId;
    }

}
