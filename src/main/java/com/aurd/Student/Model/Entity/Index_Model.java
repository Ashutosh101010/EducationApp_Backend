package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "index_data")

public class Index_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "post_id",nullable = false)
    private  int post_id;

    @Column(name = "created_on",nullable = false)
   // @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd hh:mm:ss")
    private Timestamp created_on;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name="inst_id",nullable = false)
    private Long inst_id;

    @Column(name = "added_by",nullable = false)
    private String added_by;


    @Transient
    private long timeStamp;


    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getInst_id() {
        return inst_id;
    }

    public void setInst_id(Long inst_id) {
        this.inst_id = inst_id;
    }

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

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

enum Added_By{
    employee, admin, student
}