package com.aurd.Student.Model.Entity;

import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "blog")

public class BlogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title",nullable = false)
    @NotBlank
   private String title;

    @Column(name = "description",nullable = false)
    @NotBlank
    private String description;

    @Column(name = "created_on",nullable = true)
    @Null
    @Nullable
    private Timestamp created_on;



//    @Column(name = "added_by",nullable = false)
//    private Timestamp added_by;


//    @Column(name = "created_on",nullable = true)
//    private Timestamp created_on;

    @Column(name = "inst_id",nullable = true)
    private long inst_id;

    @Column(name = "tags",nullable = false)
    @NotBlank
    private String tags;

    @Column(name = "thumbnail",nullable = false)
    private  String thumbnail;

//    @Column(name = "time",nullable = false)
//    private Long time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }
}
