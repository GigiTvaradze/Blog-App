package com.MyFirstJavaProject.blogging_app.Repository;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import com.MyFirstJavaProject.blogging_app.Entity.Subscriber;
import com.MyFirstJavaProject.blogging_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    List<Subscriber> findByBlogAndAuthenticationStatus(Blog blog, String authenticationStatus);
    List<Subscriber> findByUserAndAuthenticationStatus(User user, String authenticationStatus);
}
