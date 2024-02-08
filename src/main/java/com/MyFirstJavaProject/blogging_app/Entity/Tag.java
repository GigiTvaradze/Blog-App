package com.MyFirstJavaProject.blogging_app.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blog_tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "name", nullable = false)
    private String name;

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }


}
