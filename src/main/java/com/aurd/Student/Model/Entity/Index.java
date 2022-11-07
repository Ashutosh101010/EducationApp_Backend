package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "index_data")

public class Index {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "post_id",nullable = false)
    private  Long postId;

    @Column(name = "created_on",nullable = false)
   // @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd hh:mm:ss")
    private Timestamp createdOn;

    private String type;

    @Column(name="inst_id",nullable = false)
    private Long instId;

    @Column(name = "added_by",nullable = false)
    private String addedBy;


    @Transient
    private long timeStamp;


    @ManyToOne
   
    Institute institute;

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}

