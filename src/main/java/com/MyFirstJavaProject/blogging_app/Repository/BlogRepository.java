package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByTitleContainingIgnoreCaseOrTags_NameContainingIgnoreCase(String title, String tagName);

    List<Blog> findByUser(User user);


    // Custom query to retrieve top 10 blogs sorted by views in the last 30 days
    @Query("SELECT b FROM Blog b ")
    List<Blog> findTop10BlogsByViews();


    // Corrected query method to find inactive blogs
    @Query("SELECT b FROM Blog b WHERE b.updatedAt < :cutoffDate")
    List<Blog> findInactiveBlogs(@Param("cutoffDate") LocalDateTime cutoffDate);


    // Custom query to retrieve archived blogs
    @Query("SELECT b FROM Blog b WHERE b.archived = true")
    List<Blog> findArchivedBlogs();

    // Custom query to retrieve updated blogs
    @Query("SELECT b FROM Blog b WHERE b.updatedAt >= b.archivedAt OR b.locked = false OR b.archived = false")
    List<Blog> findUpdatedArchivedBlogs();


    // Custom query to retrieve blogs by title and/or tags
    @Query("SELECT DISTINCT b FROM Blog b LEFT JOIN b.tags t WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(t.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND b.locked = false AND b.archived = false")
    List<Blog> searchBlogsByTitleAndTags(String searchTerm);


    // Custom query to archive blogs that have not been updated in the last 1 month
    @Modifying
    @Transactional
    @Query("UPDATE Blog b SET b.archived = true, b.archivedAt = CURRENT_TIMESTAMP WHERE FUNCTION('DATED-IFF', DAY, b.updatedAt, CURRENT_DATE) >= 30 AND b.locked = false")
    void archiveOldBlogs();


    // Custom query to delete views older than 30 days
    @Modifying
    @Transactional
    @Query("DELETE FROM View v WHERE v.viewDate <= FUNCTION('DATE_SUB', CURRENT_DATE, 30, 'DAY')")
    void deleteOldViews();


    @Query("SELECT b FROM Blog b WHERE b.updatedAt < :updatedAt AND b.archived = false")
    List<Blog> findByLastUpdateBeforeAndIsArchivedFalse(LocalDateTime updatedAt);


    List<Blog> findByTitleContaining(String title);

    List<Blog> findByTags_NameIn(List<String> tagNames);

    List<Blog> findByTitleContainingAndTags_NameIn(String title, List<String> tags);


}
