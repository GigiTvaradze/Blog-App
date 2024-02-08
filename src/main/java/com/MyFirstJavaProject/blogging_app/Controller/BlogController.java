package com.MyFirstJavaProject.blogging_app.Controller;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService BlogService;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return BlogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        return BlogService.getBlogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog createdBlog = BlogService.createBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog updatedBlog) {
        return BlogService.updateBlog(id, updatedBlog)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        BlogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{blogId}/subscribe/{userId}")
    public ResponseEntity<Blog> subscribeToBlog(@PathVariable Long blogId, @PathVariable Long userId) {
        Blog blog = BlogService.subscribeToBlog(blogId, userId);
        if (blog != null) {
            return ResponseEntity.ok(blog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{blogId}/authenticateSubscriber")
    public ResponseEntity<String> authenticateSubscriber(
            @PathVariable Long blogId,
            @RequestParam Long userId,
            @RequestParam String authenticationCode) {
        try {
            boolean isAuthenticated = BlogService.authenticateSubscriber(blogId, userId, Boolean.parseBoolean(authenticationCode));

            if (isAuthenticated) {
                return ResponseEntity.ok("Authentication successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }
        } catch (Exception e) {

            return handleException(e);
        }
    }

    @PostMapping("/{blogId}/assignModerator")
    public ResponseEntity<String> assignModeratorRights(@PathVariable Long blogId, @RequestParam Long userId) {
        try {
            BlogService.assignModeratorRights(blogId, userId);
            return ResponseEntity.ok("Moderator rights assigned successfully!");
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return handleException(e);
        }
    }

    private ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
