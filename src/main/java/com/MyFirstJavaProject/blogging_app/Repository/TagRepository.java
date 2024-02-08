package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    // Custom query to retrieve tags by name
    Optional<Tag> findByName(String name);
/*
    // Custom query to retrieve tags associated with a specific blog
    @Query("SELECT t FROM Tag t JOIN t.blogTags bt WHERE bt.blog = :blog")
    List<Tag> findTagsByBlog(@Param("blog") Blog blog);


    // Custom query to retrieve all tags associated with a specific user's blogs
    @Query("SELECT DISTINCT t FROM Tag t JOIN t.blogs b WHERE b.user = :user")
    List<Tag> findTagsByUser(@Param("user") User user);

    // Custom query to retrieve all tags associated with a specific waiting user for a blog
    @Query("SELECT DISTINCT t FROM Tag t JOIN t.blogs b JOIN b.waitingUsers wu WHERE wu.user = :user AND b = :blog")
    List<Tag> findTagsByWaitingUserAndBlog(@Param("user") User user, @Param("blog") Blog blog);

 */

}
