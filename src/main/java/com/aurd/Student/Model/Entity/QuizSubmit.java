package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.runtime.annotations.IgnoreProperty;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "quiz_resp_stud")
public class QuizSubmit {

    @Id
    @GeneratedValue
    private  Long id;

    @Column(name = "stud_id ",nullable = false)
    private  Long studId;

    @Column(name = "quiz_id",nullable = false)
    private Long quizId;

    @Column(name = "ques_id",nullable = false)
    private Long quesId;

    private String ans;

    @Column(name = "marks_ob",nullable = false)
    private  Integer marksOb;

    @Column(name = "is_visited",nullable = false)
    private  Boolean is_visited;

    @Column(name = "is_ansered",nullable = false)
    private  Boolean is_ansered;

    @Column(name = "is_marked",nullable = false)
    private Boolean is_marked;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp addedOn;

    @Column(name = "inst_id",nullable = false)
    private  Long instId;

    @Column(name = "mark_for_review",nullable = false)
    private  Long markForReview;


    @Column(name = "subject_id",nullable = false)

    private  Long subjectId;

    private  String subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudId() {
        return studId;
    }

    public void setStudId(Long studId) {
        this.studId = studId;
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

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public Integer getMarksOb() {
        return marksOb;
    }

    public void setMarksOb(Integer marksOb) {
        this.marksOb = marksOb;
    }

    public Boolean getIs_visited() {
        return is_visited;
    }

    public void setIs_visited(Boolean is_visited) {
        this.is_visited = is_visited;
    }

    public Boolean getIs_ansered() {
        return is_ansered;
    }

    public void setIs_ansered(Boolean is_ansered) {
        this.is_ansered = is_ansered;
    }

    public Boolean getIs_marked() {
        return is_marked;
    }

    public void setIs_marked(Boolean is_marked) {
        this.is_marked = is_marked;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getMarkForReview() {
        return markForReview;
    }

    public void setMarkForReview(Long markForReview) {
        this.markForReview = markForReview;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
