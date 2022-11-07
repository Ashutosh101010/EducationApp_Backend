package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "student_key_notes")
public class KeyNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    @Column(name="student_id" , nullable = false)
    private Long studentId;


    @Column(name="inst_id", nullable = false)
    private Long instId;

    @Column(name="live_class_id", nullable = false)
    private Long liveClassId;



    private  String description;

    private  String category;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp addedOn;

    @Column(name = "subject_id",nullable = false)
    private Long subjectId;

    @Column(name = "course_id",nullable = false)
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "course_id",insertable = false,updatable = false)
    Course course;

    @ManyToOne
    @JoinColumn(name = "subject_id",insertable = false,updatable = false)
    Subject subject;

    @ManyToOne
    @JoinColumn(name = "live_class_id",insertable = false,updatable = false)
    LiveClass liveClass;

    @ManyToOne
    @JoinColumn(name = "student_id",insertable = false,updatable = false)
    Student student;

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getStudentId() {
        return studentId;
    }


    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getLiveClassId() {
        return liveClassId;
    }

    public void setLiveClassId(Long liveClassId) {
        this.liveClassId = liveClassId;
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

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LiveClass getLiveClass() {
        return liveClass;
    }

    public void setLiveClass(LiveClass liveClass) {
        this.liveClass = liveClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
