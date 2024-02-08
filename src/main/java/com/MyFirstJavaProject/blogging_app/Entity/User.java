package com.MyFirstJavaProject.blogging_app.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blog_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    @ManyToMany
    @JoinTable(
            name = "user_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blog_id")
    )
    private Set<Blog> likedBlogs = new HashSet<>();


    // Add methods to manage liked blogs
    public void addLikedBlog(Blog blog) {
        likedBlogs.add(blog);
        blog.getLikedByUsers().add(this);
    }

    public void removeLikedBlog(Blog blog) {
        likedBlogs.remove(blog);
        blog.getLikedByUsers().remove(this);
    }

    public Set<Blog> getLikedBlogs() {
        return likedBlogs;
    }

    public void setLikedBlogs(Set<Blog> likedBlogs) {
        this.likedBlogs = likedBlogs;
    }

}
