package com.MyFirstJavaProject.blogging_app.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "blog_blogs")
public class Blog {

    @ManyToMany
    @JoinTable(
            name = "blog_tags",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "blog")
    private List<Subscriber> subscribers;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments;

    @OneToMany(mappedBy = "blog")
    private List<Like> likes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "locked", nullable = false)
    private boolean locked;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Subscriber> subscribersList;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    @ManyToMany(mappedBy = "likedBlogs")
    private Set<User> likedByUsers = new HashSet<>();

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private Set<Moderator> moderators = new HashSet<>();

    public Blog() {
        this.tags = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.likedByUsers = new HashSet<>();
        this.moderators = new HashSet<>();
    }

    // Getter and setter methods

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public LocalDateTime getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(LocalDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public Set<User> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(Set<User> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public void addLike(User user) {
        likedByUsers.add(user);
        user.getLikedBlogs().add(this);
    }

    public void removeLike(User user) {
        likedByUsers.remove(user);
        user.getLikedBlogs().remove(this);
    }

    public Set<Moderator> getModerators() {
        return moderators;
    }

    public void addModerator(User user) {
        Moderator moderator = new Moderator(user, this);
        moderators.add(moderator);
    }

    public void removeModerator(User user) {
        moderators.removeIf(m -> m.getUser().equals(user));
    }

    public LocalDateTime getLastUpdate() {
        return updatedAt;
    }

    public void setLastUpdate(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


}
