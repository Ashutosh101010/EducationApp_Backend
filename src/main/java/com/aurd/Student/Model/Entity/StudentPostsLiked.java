package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "student_posts_liked")
public class StudentPostsLiked {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "post_id",nullable = false)
    private int postId;

    @Column(name = "added_by",nullable = false)
    private int addedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(int addedBy) {
        this.addedBy = addedBy;
    }
}
