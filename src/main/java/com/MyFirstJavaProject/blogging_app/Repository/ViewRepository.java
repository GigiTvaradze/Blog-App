package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import com.MyFirstJavaProject.blogging_app.Entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ViewRepository extends JpaRepository<View, Long> {

    static void deleteByCreatedAtBefore(LocalDate thirtyDaysAgo) {
    }

    // Custom query to retrieve views for a specific blog
    List<View> findViewsByBlog(Blog blog);

    // Custom query to retrieve views by a specific user
    List<View> findViewsByUser(User user);

    // Custom query to check if a user has viewed a specific blog
    @Query("SELECT v FROM View v WHERE v.blog = :blog AND v.user = :user")
    List<View> findViewsByBlogAndUser(@Param("blog") Blog blog, @Param("user") User user);

    // Custom query to retrieve the total number of views for a specific blog
    @Query("SELECT COUNT(v) FROM View v WHERE v.blog = :blog")
    long countViewsByBlog(@Param("blog") Blog blog);

    // Custom query to delete views older than 30 days
    @Modifying
    @Query("DELETE FROM View v WHERE v.viewDate < :cutoffDate")
    void deleteOldViews(@Param("cutoffDate") LocalDateTime cutoffDate);

}
