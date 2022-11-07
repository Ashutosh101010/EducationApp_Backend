package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "test_series_resp_stud")
public class TestSeriesSubmit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stud_id ", nullable = false)
    private Long studId;

    @Column(name = "quiz_id", nullable = false)
    private Long quizId;

    @Column(name = "ques_id", nullable = false)
    private Long quesId;

    private String ans;

    @Column(name = "marks_ob", nullable = false)
    private int marksOb;

    @Column(name = "is_visited", nullable = false)
    private Boolean isVisited;

    @Column(name = "is_ansered", nullable = false)
    private Boolean isAnsered;

    @Column(name = "is_marked", nullable = false)
    private Boolean isMarked;

    @Column(name = "added_on", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp addedOn;

    @Column(name = "inst_id", nullable = false)
    private Long instId;

    @Column(name = "markForReview", nullable = false)
    private Integer markForReview;


    @Column(name = "subject_id", nullable = false)

    private Long subjectId;

    private String subject;

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

    public int getMarksOb() {
        return marksOb;
    }

    public void setMarksOb(int marksOb) {
        this.marksOb = marksOb;
    }

    public Boolean getVisited() {
        return isVisited;
    }

    public void setVisited(Boolean visited) {
        isVisited = visited;
    }

    public Boolean getAnsered() {
        return isAnsered;
    }

    public void setAnsered(Boolean ansered) {
        isAnsered = ansered;
    }

    public Boolean getMarked() {
        return isMarked;
    }

    public void setMarked(Boolean marked) {
        isMarked = marked;
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

    public Integer getMarkForReview() {
        return markForReview;
    }

    public void setMarkForReview(Integer markForReview) {
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


