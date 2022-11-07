package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Table(name = "quiz_ques_map_for_series")
public class TestSeriesQuestionMap {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "quiz_id", nullable = false)
    private Long quizId;


    @Column(name = "ques_id", nullable = false)
    private Long quesId;

    private Integer marks;

    @Column(name = "subject_id", nullable = false)
    private long subjectId;


    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Subject subject;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
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

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
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


