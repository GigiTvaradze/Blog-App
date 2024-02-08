package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.Comment;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Custom query to retrieve comments for a specific blog
    List<Comment> findCommentsByBlog(Blog blog);

    // Custom query to retrieve comments by a specific user
    List<Comment> findCommentsByUser(User user);

    // Custom query to retrieve comments for a specific blog and user
    @Query("SELECT c FROM Comment c WHERE c.blog = :blog AND c.user = :user")
    List<Comment> findCommentsByBlogAndUser(@Param("blog") Blog blog, @Param("user") User user);



}
