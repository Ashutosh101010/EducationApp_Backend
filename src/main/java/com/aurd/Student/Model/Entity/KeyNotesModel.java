package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "student_key_notes")
public class KeyNotesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title", nullable = false)
    @NotBlank
    private String title;

    @Column(name="student_id" , nullable = false)
    private int student_id;


    @Column(name="inst_id", nullable = false)
    private int inst_id;

    @Column(name="live_class_id", nullable = false)
    private int live_class_id;


    @Column(name = "student_name",nullable = false)
    @NotBlank
    private String student_name;

    @Column(name = "description",nullable = false)
    @NotBlank
    private  String description;

    @Column(name ="category",nullable = false)
    @NotBlank
    private  String category;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp added_on;

    @Column(name = "subject_id",nullable = false)
    private int subject_id;

    @Column(name = "course_id",nullable = false)
    private int course_id;

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

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

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getLive_class_id() {
        return live_class_id;
    }

    public void setLive_class_id(int live_class_id) {
        this.live_class_id = live_class_id;
}

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }
}
