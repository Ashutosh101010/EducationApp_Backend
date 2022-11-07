package com.aurd.Student.Model.Entity.map;


import com.aurd.Student.Model.Entity.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.runtime.annotations.IgnoreProperty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Max;


@Entity
@Table(name = "quiz_ques_map")
public class Quiz_Question_Map_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;



    @Column(name = "quiz_id",nullable = false)
    private Long quiz_id;


    @Column(name = "ques_id",nullable = false)
    private Long quesId;

    private  Integer marks;

    @Column(name = "subject_id",nullable = false)
    private Long subjectId;

    @Column(name = "updated_by",nullable = false)
    private Long updatedBy;

    @ManyToOne
    @JoinColumn(name="subject_id", nullable=false,insertable = false,updatable = false)
    @JsonIgnore
    private Subject subject;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
