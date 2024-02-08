package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.Subscriber;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import com.MyFirstJavaProject.blogging_app.Repository.BlogRepository;
import com.MyFirstJavaProject.blogging_app.Repository.SubscriberRepository;
import com.MyFirstJavaProject.blogging_app.Repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private static BlogRepository blogRepository;

    @Autowired
    private static SubscriberRepository SubscriberRepository;

    @Autowired
    private UserService userService;


    public static Blog findById(Long blogId) {
        return blogRepository.findById(blogId).orElse(null);
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Midnight on the first day of each month
    public void archiveInactiveBlogs() {
        LocalDateTime updatedAt = LocalDateTime.now().minusMonths(1);


        List<Blog> inactiveBlogs = blogRepository.findByLastUpdateBeforeAndIsArchivedFalse(updatedAt);
        inactiveBlogs.forEach(blog -> {
            blog.setArchivedAt(LocalDateTime.now());
            blogRepository.save(blog);
        });
    }

    @Scheduled(cron = "0 0 0 * * ?") // Midnight every day
    public void deleteOldViews() {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        ViewRepository.deleteByCreatedAtBefore(thirtyDaysAgo);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Optional<Blog> updateBlog(Long id, Blog updatedBlog) {
        return blogRepository.findById(id)
                .map(existingBlog -> {
                    // Update blog properties
                    existingBlog.setTitle(updatedBlog.getTitle());
                    existingBlog.setContent(updatedBlog.getContent());
                    existingBlog.setLocked(updatedBlog.isLocked());
                    // Update other properties as needed

                    return blogRepository.save(existingBlog);
                });
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @PostMapping("/some-endpoint")
    public ResponseEntity<String> someEndpoint(@RequestParam Long blogId, @RequestParam Long userId, @RequestParam String authenticationCode) {
        // Attempt to authenticate the subscriber
        boolean isAuthenticated = BlogService.authenticateSubscriber(blogId, userId, Boolean.parseBoolean(authenticationCode));

        // Check the result of authentication
        if (isAuthenticated) {
            // Continue with the logic for authenticated users
            return ResponseEntity.ok("Authentication successful!");
        } else {
            // Handle the case where authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed!");
        }
    }


    public Blog subscribeToBlog(Long blogId, Long userId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null && !blog.isLocked()) {
            // Blog is not locked, so no need to subscribe
            return blog;
        }

        // Check if the user is already subscribed
        assert blog != null;
        if (!isUserSubscribed(blog, userId)) {
            Subscriber subscriber = new Subscriber();
            subscriber.setBlog(blog);
            subscriber.setUser(UserService.findById(userId)); // You need to implement this method in UserService
            subscriber.setAuthenticationStatus("PENDING"); // You can use different statuses like "PENDING", "APPROVED", etc.
            SubscriberRepository.save(subscriber);
        }

        return blog;
    }

    private boolean isUserSubscribed(Blog blog, Long userId) {
        // Check if the user is already subscribed to the blog
        return blog.getSubscribers().stream().anyMatch(subscriber -> subscriber.getUser().getId().equals(userId));
    }

    public static boolean authenticateSubscriber(Long blogId, Long subscriberId, boolean approve) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        Subscriber subscriber = SubscriberRepository.findById(subscriberId).orElse(null);

        if (blog != null && subscriber != null && blog.getSubscribers().contains(subscriber)) {
            if (approve) {
                // Approve the subscriber
                subscriber.setAuthenticationStatus("APPROVED");
            } else {
                // Deny the subscriber
                subscriber.setAuthenticationStatus("DENIED");
            }

            SubscriberRepository.save(subscriber);
            return true; // Authentication successful
        }

        return false; // Authentication failed
    }

    public List<Blog> searchBlogs(String title, List<String> tags) {
        if (title != null && tags != null && !tags.isEmpty()) {
            // Search by both title and tags
            return blogRepository.findByTitleContainingAndTags_NameIn(title, tags);
        } else if (title != null) {
            // Search by title only
            return blogRepository.findByTitleContaining(title);
        } else if (tags != null && !tags.isEmpty()) {
            // Search by tags only
            return blogRepository.findByTags_NameIn(tags);
        } else {
            // No search criteria provided
            return blogRepository.findAll();
        }
    }

    public void addModerator(Long blogId, Long userId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        User user = UserService.findById(userId); // Implement userService.findById

        if (blog != null && user != null) {
            blog.addModerator(user);
            blogRepository.save(blog);
        }
    }

    public void removeModerator(Long blogId, Long userId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        User user = UserService.findById(userId); // Implement userService.findById

        if (blog != null && user != null) {
            blog.removeModerator(user);
            blogRepository.save(blog);
        }
    }

    public void assignModeratorRights(Long blogId, Long userId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        User user = UserService.findById(userId); // Implement userService.findById

        if (blog != null && user != null) {
            blog.addModerator(user);
            blogRepository.save(blog);
        }
    }

}
