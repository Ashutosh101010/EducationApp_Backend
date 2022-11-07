package com.aurd.Student.Model.Entity;


import org.checkerframework.checker.units.qual.C;
import org.hibernate.cfg.ImprovedNamingStrategy;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "audios")
public class Audio {


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String audio;
    private String thumb;


    private Long topicId;
    @Transient
    Long timestamp;

    String userType;


    private Long instId;

    private String description;

    //    private String video_type;
    private Long subjectId;
    private Long subSubjectId;
    private Long courseId;
    private String feeType;


    private int createdBy;
    private Timestamp createdAt;
    private String updatedAt;



//    @ManyToOne
//   
//    Institute institute;

//    public Institute getInstitute() {
//        return institute;
//    }
//
//    public void setInstitute(Institute institute) {
//        this.institute = institute;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    @Column(name = "topic_id")
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    @Column(name = "user_type")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(name = "inst_id")
    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "subject_id")
    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name = "sub_subject_id")
    public Long getSubSubjectId() {
        return subSubjectId;
    }

    public void setSubSubjectId(Long subSubjectId) {
        this.subSubjectId = subSubjectId;
    }

    @Column(name = "course_id")
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Column(name = "fee_type")
    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @Column(name = "created_by")
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}