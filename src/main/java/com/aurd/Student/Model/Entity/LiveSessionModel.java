//package com.aurd.Student.Model.Entity;
//
//import org.hibernate.annotations.NotFound;
//import org.hibernate.annotations.NotFoundAction;
//
//import javax.persistence.*;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.NotBlank;
//
//@Entity
//@Table(name = "live_session`")
//
//
//public class LiveSessionModel {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long live_session_id;
//
//
//    @Column(name = "course",nullable = false)
//    @NotBlank
//    private String course;
//
//    @Column(name = "title",nullable = false)
//    @NotBlank
//    @Max(256)
//    private String title;
//
//    @Column(name = "status",nullable = false)
//    @NotBlank
//    @Max(256)
//    private int status;
//
//    @Column(name="subject",nullable = false)
//    @NotBlank
//    private String subject;
//
//    @Column(name = "faculty_id",nullable = false)
//    @NotBlank
//    private int faculty_id;
//
//
//
//
//    public long getLive_session_id() {
//        return live_session_id;
//    }
//
//    public void setLive_session_id(long live_session_id) {
//        this.live_session_id = live_session_id;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public int getFaculty_id() {
//        return faculty_id;
//    }
//
//    public void setFaculty_id(int faculty_id) {
//        this.faculty_id = faculty_id;
//    }
//
//    @Override
//    public String toString() {
//        return "LiveSessionModel{" +
//                "live_session_id=" + live_session_id +
//                ", course='" + course + '\'' +
//                ", title='" + title + '\'' +
//                ", status=" + status +
//                ", subject='" + subject + '\'' +
//                ",faculty_id='"+faculty_id+'\'' +
//                '}';
//    }
//}
