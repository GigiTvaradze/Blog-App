package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import com.MyFirstJavaProject.blogging_app.Entity.WaitingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WaitingUserRepository extends JpaRepository<WaitingUser, Long> {

    // Custom query to retrieve waiting users for a specific blog
    List<WaitingUser> findWaitingUsersByBlog(Blog blog);

    // Custom query to retrieve waiting users by a specific user
    List<WaitingUser> findWaitingUsersByUser(User user);

    // Custom query to check if a user is in the waiting list for a specific blog
    @Query("SELECT w FROM WaitingUser w WHERE w.blog = :blog AND w.user = :user")
    Optional<WaitingUser> findWaitingUserByBlogAndUser(@Param("blog") Blog blog, @Param("user") User user);

    // Custom query to retrieve the total number of waiting users for a specific blog
    @Query("SELECT COUNT(w) FROM WaitingUser w WHERE w.blog = :blog")
    long countWaitingUsersByBlog(@Param("blog") Blog blog);

}
