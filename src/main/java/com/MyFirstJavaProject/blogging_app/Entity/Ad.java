package com.MyFirstJavaProject.blogging_app.Entity;

import com.MyFirstJavaProject.blogging_app.Entity.Blog;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "blog_ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private Long adId;

    @Column(name = "rpm", nullable = false)
    private BigDecimal rpm;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    public void setAdId(Long adId) {
        this.adId = adId;
    }}
