package com.aurd.Student.Model.Entity;

import io.smallrye.common.constraint.Nullable;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "notes")
public class Notes {
    @Id
    @GeneratedValue
    private Long id;

    private  String name;


    private String file;


    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "created_at")
    private Timestamp createdAt;


    @Column(name = "inst_id")
    private Long instId;

    @Column(name = "created_by")
    private Long createdBy;


    private  String description;


    @Column(name = "subject_id")
    private  Long subjectId;

    @Column(name = "course_id")
    private  String courseId;

    @Column(name = "sub_subject_id")
    private  String subSubjectId;

    @Column(name = "fee_type")
    private String feeType;

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSubSubjectId() {
        return subSubjectId;
    }

    public void setSubSubjectId(String subSubjectId) {
        this.subSubjectId = subSubjectId;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
}
