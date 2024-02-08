package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Ad;
import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    // Custom query to retrieve ads for a specific blog
    List<Ad> findAdsByBlog(Blog blog);

    // Custom query to retrieve ads with RPM greater than a specified value for a specific blog
    @Query("SELECT a FROM Ad a WHERE a.blog = :blog AND a.rpm > :minRpm")
    List<Ad> findAdsByBlogAndMinRpm(@Param("blog") Blog blog, @Param("minRpm") Double minRpm);

    // Custom query to retrieve ads with RPM less than a specified value for a specific blog
    @Query("SELECT a FROM Ad a WHERE a.blog = :blog AND a.rpm < :maxRpm")
    List<Ad> findAdsByBlogAndMaxRpm(@Param("blog") Blog blog, @Param("maxRpm") Double maxRpm);

    // Custom query to retrieve ads with RPM within a specified range for a specific blog
    @Query("SELECT a FROM Ad a WHERE a.blog = :blog AND a.rpm BETWEEN :minRpm AND :maxRpm")
    List<Ad> findAdsByBlogAndRpmRange(@Param("blog") Blog blog, @Param("minRpm") Double minRpm, @Param("maxRpm") Double maxRpm);

}
