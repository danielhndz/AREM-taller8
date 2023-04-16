package edu.escuelaing.arem.model;

import java.time.LocalDateTime;

public class Tweet {

    private int id;
    private String content;
    private User author;
    private LocalDateTime createdAt;

    public Tweet() {
        /*
         * Quarkus:
         * A default constructor is required by the JSON serialization layer.
         */
    }

    public Tweet(int id, String content, User author, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
