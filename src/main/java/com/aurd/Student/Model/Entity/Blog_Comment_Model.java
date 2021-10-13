package com.aurd.Student.Model.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "blog_commented")

public class Blog_Comment_Model {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @Column(name = "comment",nullable = false)
    private String comment;

    @Column(name = "blog_id",nullable = false)
    private int blog_id;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp added_on;

    @Column(name = "added_by",nullable = false)
    private int added_by;

    @Column(name = "type",nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "added_by",insertable = false,updatable = false)
    StudentModel studentModel;

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //    @Column(name = "stud_name",nullable = false)
//    @NotBlank
//    private String stud_name;
//
//
//    public String getStud_name() {
//        return stud_name;
//    }
//
//    public void setStud_name(String stud_name) {
//        this.stud_name = stud_name;
//    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBlog_id_id() {
        return blog_id;
    }

    public void setBlog_id(int post_id) {
        this.blog_id = post_id;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }
}







