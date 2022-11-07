package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "quiz_sections")
public class QuizSection {

    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "subject_id",nullable = false)
    private Long subjectId;

    @Column(name = "quiz_id",nullable = false)
    private Long quizId;

    @Column(name = "subject_cutoff",nullable = false)
    private Integer subjectCutoff;

    @Column(name = "subject_total",nullable = false)
    private Integer subjectTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Integer getSubjectCutoff() {
        return subjectCutoff;
    }

    public void setSubjectCutoff(Integer subjectCutoff) {
        this.subjectCutoff = subjectCutoff;
    }

    public Integer getSubjectTotal() {
        return subjectTotal;
    }

    public void setSubjectTotal(Integer subjectTotal) {
        this.subjectTotal = subjectTotal;
    }
}



