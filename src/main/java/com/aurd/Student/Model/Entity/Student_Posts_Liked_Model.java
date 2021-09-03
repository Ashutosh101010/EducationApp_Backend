package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "student_posts_liked")
public class Student_Posts_Liked_Model {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "post_id",nullable = false)
    private int post_id;

    @Column(name = "added_by",nullable = false)
    private int added_by;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }
}
