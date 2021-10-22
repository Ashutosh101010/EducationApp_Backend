package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;


@Entity
@Table(name = "live_session")

public class LiveClassModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long live_session_id;


    @Column(name = "course",nullable = false)
    @NotBlank
    @Max(256)
    private String course;


    @Column(name = "title",nullable = false)
    @NotBlank
    @Max(256)
    private String title;


    @Column(name = "batch",nullable = false)
    @NotBlank
    @Max(256)
    private String batch;

    @Column(name = "subject",nullable = false)
    @NotBlank
    @Max(256)
    private String subject;


    @Column(name = "sub_subject",nullable = false)
    @NotBlank
    @Max(256)
    private String sub_subject;

    @Column(name = "topic",nullable = false)
    @NotBlank
    @Max(256)
    private String topic;

    @Column(name = "quiz_id",nullable = false)
    private int quiz_id;


    @Column(name = "inst_id",nullable = false)
    private int inst_id;

    @Column(name = "faculty_id",nullable = false)
    private int faculty_id;

    @Column(name = "live_session_url",nullable = true)
    @Max(255)
    private String live_session_url;

    @Column(name = "status",nullable = false)
    private int status;

    @Column(name = "date_time",nullable = false)
    private Timestamp date_time;

    @Column(name = "start_time",nullable = false)
    private Timestamp start_time;

    @Column(name = "end_time",nullable = false)
    private Timestamp end_time;


    public long getLive_session_id() {
        return live_session_id;
    }

    public void setLive_session_id(long live_session_id) {
        this.live_session_id = live_session_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSub_subject() {
        return sub_subject;
    }

    public void setSub_subject(String sub_subject) {
        this.sub_subject = sub_subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(int faculty_id) {
        this.faculty_id = faculty_id;
    }

    public String getLive_session_url() {
        return live_session_url;
    }

    public void setLive_session_url(String live_session_url) {
        this.live_session_url = live_session_url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }
}
