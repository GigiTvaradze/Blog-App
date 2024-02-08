package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.Moderator;
import com.MyFirstJavaProject.blogging_app.Entity.ModeratorId;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModeratorRepository extends JpaRepository<Moderator, ModeratorId> {

    // Custom query to retrieve moderators for a specific blog
    List<Moderator> findModeratorsByBlog(Blog blog);

    // Custom query to retrieve blogs moderated by a specific user
    List<Moderator> findModeratorsByUser(User user);

    // Custom query to check if a user is a moderator for a specific blog
    @Query("SELECT m FROM Moderator m WHERE m.blog = :blog AND m.user = :user")
    List<Moderator> findModeratorsByBlogAndUser(@Param("blog") Blog blog, @Param("user") User user);

    // Custom query to retrieve the total number of moderators for a specific blog
    @Query("SELECT COUNT(m) FROM Moderator m WHERE m.blog = :blog")
    long countModeratorsByBlog(@Param("blog") Blog blog);


    Optional<Moderator> findById(ModeratorId id);

    @Override
    default <S extends Moderator> S save(S entity) {
        return null;
    }

    @Override
    void deleteById(ModeratorId id);

    @Override
    List<Moderator> findAll();
}
