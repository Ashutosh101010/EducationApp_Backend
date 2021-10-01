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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd hh:mm:ss")
    private Timestamp created_on;

    @Column(name = "type",nullable = false)
    private String type;


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
