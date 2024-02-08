package com.MyFirstJavaProject.blogging_app.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "blog_subscriber")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "authentication_status")
    private String authenticationStatus;

    public void setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public Subscriber() {
    }

    public Subscriber(Blog blog, User user, String authenticationStatus) {
        this.blog = blog;
        this.user = user;
        this.authenticationStatus = authenticationStatus;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
