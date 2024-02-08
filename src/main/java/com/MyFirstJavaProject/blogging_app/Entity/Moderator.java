package com.MyFirstJavaProject.blogging_app.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(ModeratorId.class)
@Table(name = "blog_moderators")
public class Moderator implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "blog_id", nullable = false)
    private Blog blog;


    public Moderator(User user, Blog blog) {
        this.user = user;
        this.blog = blog;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moderator moderator = (Moderator) o;
        return Objects.equals(user, moderator.user) &&
                Objects.equals(blog, moderator.blog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, blog);
    }

}
