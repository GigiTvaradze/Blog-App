package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.Like;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // Custom query to retrieve likes for a specific blog
    List<Like> findLikesByBlog(Blog blog);

    // Custom query to retrieve likes by a specific user
    List<Like> findLikesByUser(User user);

    // Custom query to check if a user has already liked a specific blog
    @Query("SELECT l FROM Like l WHERE l.blog = :blog AND l.user = :user")
    Optional<Like> findLikeByBlogAndUser(@Param("blog") Blog blog, @Param("user") User user);

    // Custom query to retrieve the total number of likes for a specific blog
    @Query("SELECT COUNT(l) FROM Like l WHERE l.blog = :blog")
    long countLikesByBlog(@Param("blog") Blog blog);

}
