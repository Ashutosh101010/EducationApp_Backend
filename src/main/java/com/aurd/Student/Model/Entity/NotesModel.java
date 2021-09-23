package com.aurd.Student.Model.Entity;

import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "notes")
public class NotesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    @NotBlank
    private  String name;


    @Column(name = "file",nullable = false)
    @NotBlank
    private String file;


    @Column(name = "topicId",nullable = false)
    private Integer topicId;

    @Column(name = "created_at",nullable = true)
    private Timestamp created_at;


    @Column(name = "inst_id",nullable = false)
    @Max(12)
    private int inst_id;

    @Column(name = "created_by",nullable = false)
    @Max(12)
    private int created_by;


    @Column(name = "description",nullable = false)
    private  String description;


    @Column(name = "subject_id",nullable = false)
    private  Integer subject_id;

    @Column(name = "course_id",nullable = true)
    private  String course_id;

    @Column(name = "sub_subject_id",nullable = true)
    private  String sub_subject_id;

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getSub_subject_id() {
        return sub_subject_id;
    }

    public void setSub_subject_id(String sub_subject_id) {
        this.sub_subject_id = sub_subject_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //    private  String teacherName;
//
//    public String getTeacherName() {
//        return teacherName;
//    }
//
//    public void setTeacherName(String teacherName) {
//        this.teacherName = teacherName;
//    }

    public Timestamp getCreated_at() {
        return created_at;
    }


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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }



    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }
}
