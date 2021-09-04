package com.aurd.Student.Model.Entity;

import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "student_posts")
public class StudentPostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "discription",nullable = false)
    private String discription;

    @Column(name = "pic",nullable = false)
    private String pic;

    @Column(name = "post_status",nullable = false)
    private int post_status;

    @Column(name = "added_by",nullable = false)
    private  int added_by;

    @Column(name = "added_on",nullable = false)
    private Timestamp added_on;

    @Column(name = "inst_id",nullable = false)
    private  int inst_id;

    @Column(name = "post_approved_by",nullable = true)
    @Nullable
    @Null
    private Integer post_approved_by;

//    @Column(name = "post_approved_on",nullable = true)
//    @Nullable
//    @Null
//    private Timestamp post_approved_on;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPost_status() {
        return post_status;
    }

    public void setPost_status(int post_status) {
        this.post_status = post_status;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }


    public Integer getPost_approved_by() {
        return post_approved_by;
    }

    public void setPost_approved_by(Integer post_approved_by) {
        this.post_approved_by = post_approved_by;
    }

//    public void setPost_approved_on(Timestamp post_approved_on) {
//        this.post_approved_on = post_approved_on;
//    }
}
