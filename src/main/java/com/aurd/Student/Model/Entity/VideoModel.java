package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.sql.Timestamp;

@Entity
@Table(name = "videos")
public class VideoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "name",nullable = false)
    @Max(256)
    private String name;

    @Column(name = "video",nullable = false)
    @Max(256)
    private  String video;

    @Column(name = "thumb",nullable = false)
    @Max(256)
    private  String thumb;

    @Column(name = "topicId",nullable = false)
    private  long topicId;

    @Column(name = "created_at",nullable = false)
    private Timestamp created_at;

    @Column(name = "teacher_id",nullable = false)
    private long teacher_id;

    @Column(name = "description",nullable = true)
    private String description;

    @Column(name = "tags",nullable = true)
    private String tags;

    @Column(name = "video_type",nullable = false)
    private String video_type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }
}
