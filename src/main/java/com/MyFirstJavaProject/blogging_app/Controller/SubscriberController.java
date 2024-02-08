package com.MyFirstJavaProject.blogging_app.Controller;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.Subscriber;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import com.MyFirstJavaProject.blogging_app.Service.BlogService;
import com.MyFirstJavaProject.blogging_app.Service.SubscriberService;
import com.MyFirstJavaProject.blogging_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscribers")
public class SubscriberController {

    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping("/blog/{blogId}/status/{status}")
    public List<Subscriber> getSubscribersByBlogAndStatus(
            @PathVariable Long blogId,
            @PathVariable String status) {
        // Fetch the Blog entity by blogId (not shown, assume it's available)
        Blog blog = BlogService.findById(blogId);
        return subscriberService.getSubscribersByBlogAndStatus(blog, status);
    }

    @GetMapping("/user/{userId}/status/{status}")
    public List<Subscriber> getSubscribersByUserAndStatus(
            @PathVariable Long userId,
            @PathVariable String status) {
        // Fetch the User entity by userId (not shown, assume it's available)
        User user = UserService.findById(userId);
        return subscriberService.getSubscribersByUserAndStatus(user, status);
    }

}
