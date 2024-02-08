package com.MyFirstJavaProject.blogging_app.Service;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.Subscriber;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import com.MyFirstJavaProject.blogging_app.Repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public List<Subscriber> getSubscribersByBlogAndStatus(Blog blog, String authenticationStatus) {
        return subscriberRepository.findByBlogAndAuthenticationStatus(blog, authenticationStatus);
    }

    public List<Subscriber> getSubscribersByUserAndStatus(User user, String authenticationStatus) {
        return subscriberRepository.findByUserAndAuthenticationStatus(user, authenticationStatus);
    }

}
