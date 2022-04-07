package com.aurd.Student.Model.Entity;

import io.quarkus.runtime.annotations.IgnoreProperty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;


@Entity
@Table(name = "live_session")

public class LiveClassModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int live_session_id;


    @Column(name = "course",nullable = false)
    private int course;


    @Column(name = "title",nullable = false)
    private String title;


    @Column(name = "batch",nullable = false)
    private String batch;

    @Column(name = "subject",nullable = false)
    private String subject;


    @Column(name = "sub_subject",nullable = false)
    private String sub_subject;

    @Column(name = "topic",nullable = false)
    private String topic;

    @Column(name = "quiz_id",nullable = false)
    private int quiz_id;


    @Column(name = "inst_id",nullable = false)
    private int inst_id;

    @Column(name = "faculty_id",nullable = false)
    private int faculty_id;

//    @Column(name = "live_session_url",nullable = true)
//    private String live_session_url;

    @Column(name = "status",nullable = false)
    private int status;

    @Column(name = "date_time",nullable = false)
    private Timestamp date_time;

    @Column(name = "start_time",nullable = false)
    private Timestamp start_time;

    @Column(name = "end_time",nullable = false)
    private Timestamp end_time;

    @Column(name = "thumbnail",nullable = false)
    private String thumbnail;

    @Column(name = "fee_type",nullable = true)
    private String fee_type;

    @OneToOne
    @JoinColumn(name = "youtubeLiveSession")
    @NotFound(action = NotFoundAction.IGNORE)
    private YoutubeLiveSession youtubeLiveSession;


    public YoutubeLiveSession getYoutubeLiveSession() {
        return youtubeLiveSession;
    }

    public void setYoutubeLiveSession(YoutubeLiveSession youtubeLiveSession) {
        this.youtubeLiveSession = youtubeLiveSession;
    }

    @Transient
    String courseName;

    @Transient
    String teacherName;

    @Transient
    String image;

    @Transient
    boolean purchased;



    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getLive_session_id() {
        return live_session_id;
    }

    public void setLive_session_id(int live_session_id) {
        this.live_session_id = live_session_id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
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
