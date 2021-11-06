package com.aurd.Student.Model.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment_reply")

public class Comment_Reply_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "comment_id",nullable = false)
    private int comment_id;


    @Column(name = "post_id",nullable = false)
    private int post_id;

    @Column(name = "user_id",nullable = false)
    private int user_id;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp added_on;

    @Column(name = "comment_reply",nullable = false)
    private String comment_reply;

    @Column(name = "type",nullable = false)
    private String type;


    @Column(name = "user_type",nullable = false)
    private String user_type;

    @Transient
    private  String fname;

    @Transient
    private String time;

    @Transient
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    StudentModel studentModel;


    @ManyToOne
    @JoinColumn(name="user_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    TeacherModel teacherModel;


//    @Column(name = "fname",nullable = false)
//    private  String fname;


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    public TeacherModel getTeacherModel() {
        return teacherModel;
    }

    public void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
    }

    public int getComment_id() {
        return comment_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public String getComment_reply() {
        return comment_reply;
    }

    public void setComment_reply(String comment) {
        this.comment_reply = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setComment_id(int comment_id) {
        this.comment_id=comment_id;
    }

//    public String getFname() {
//        return fname;
//    }
//
//    public void setFname(String fname) {
//        this.fname = fname;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
