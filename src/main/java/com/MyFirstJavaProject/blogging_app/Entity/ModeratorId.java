package com.MyFirstJavaProject.blogging_app.Entity;

import jakarta.persistence.IdClass;

import java.io.Serializable;
import java.util.Objects;

@IdClass(ModeratorId.class)
public class ModeratorId implements Serializable {

    private Long user;
    private Long blog;

    public ModeratorId() {
    }

    public ModeratorId(Long userId, Long blogId) {
        this.user = userId;
        this.blog = blogId;
    }

    public Long getUserId() {
        return user;
    }

    public void setUserId(Long userId) {
        this.user = userId;
    }

    public Long getBlogId() {
        return blog;
    }

    public void setBlogId(Long blogId) {
        this.blog = blogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModeratorId that = (ModeratorId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(blog, that.blog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, blog);
    }
}
