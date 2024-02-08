package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query to retrieve a user by username
    Optional<User> findByUsername(String username);

    /*
    // Custom query to retrieve blogs created by a specific user
    List<Blog> findBlogsByUser(User user);

    // Custom query to retrieve waiting users for a specific blog
    @Query("SELECT u FROM User u JOIN u.waitingBlogs w WHERE w.blog = :blog")
    List<User> findWaitingUsersForBlog(@Param("blog") Blog blog);

     */

    // Custom query to retrieve blogs moderated by a specific user
    @Query("SELECT b FROM Blog b JOIN b.moderators m WHERE m.user = :user")
    List<Blog> findBlogsModeratedByUser(@Param("user") User user);

    // Corrected query method to find top 10 blogs by views
    @Query("SELECT v.blog FROM View v GROUP BY v.blog ORDER BY COUNT(v.blog) DESC")
    List<Blog> findTop10BlogsByViews();

    // Custom query to delete views that are older than 30 days
    @Modifying
    @Query(value = "DELETE FROM View v WHERE v.viewDate <= current_date - interval '30' day", nativeQuery = true)
    default void deleteOldViews(@Param("cutoffDate") LocalDateTime cutoffDate) {

    }


/*
    // Custom query to find a user by username or email
    @Query("SELECT u FROM User u WHERE u.username = :identifier OR u.email = :identifier")
    Optional<User> findByUsernameOrEmail(@Param("identifier") String identifier);
*/
}
