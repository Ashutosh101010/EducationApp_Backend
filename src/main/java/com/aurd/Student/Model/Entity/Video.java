package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.sql.Timestamp;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private  String video;

    private  String thumb;

    @Column(name = "topic_id",nullable = false)
    private  Integer topicId;

    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;

    @Column(name = "created_by",nullable = false)
    private Long createdBy;

    private String description;

    @Column(name = "fee_type",nullable = true)
    private String feeType;

    @Column(name = "course_id",nullable = true)
    private Long courseId;

    @Column(name = "sub_subject_id",nullable = true)
    private Long subSubjectId;

    @Column(name = "subject_id",nullable = true)
    private Long subjectId;


    @Column(name = "inst_id",nullable = false)
    private Long instId;

    private String ldVideo;
    private String sdVideo;
    private String hdVideo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSubSubjectId() {
        return subSubjectId;
    }

    public void setSubSubjectId(Long subSubjectId) {
        this.subSubjectId = subSubjectId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getLdVideo() {
        return ldVideo;
    }

    public void setLdVideo(String ldVideo) {
        this.ldVideo = ldVideo;
    }

    public String getSdVideo() {
        return sdVideo;
    }

    public void setSdVideo(String sdVideo) {
        this.sdVideo = sdVideo;
    }

    public String getHdVideo() {
        return hdVideo;
    }

    public void setHdVideo(String hdVideo) {
        this.hdVideo = hdVideo;
    }
}
