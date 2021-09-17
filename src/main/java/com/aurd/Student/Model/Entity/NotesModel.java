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

    @Column(name = "teacher_id",nullable = false)
    @Max(12)
    private int teacher_id;





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

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }
}
