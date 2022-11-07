package com.aurd.Student.Model.Entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "result")

public class Result {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "stud_id",nullable = false)
    private Long studId;

    @Column(name = "inst_id",nullable = false)
    private Long instId;

    @Column(name = "quiz_id",nullable = false)
    private  Long quizId;


    @Column(name = "total_marks",nullable = false)
    private Long totalMarks;

    @Column(name = "marks_obtained",nullable = false)
    private  Double marksObtained;

    @Column(name = "correct_ans",nullable = false)
    private  Integer correctAns;

    @Column(name = "wrong_ans",nullable = false)
    private  Integer wrongAns;

    @Column(name = "cut_off",nullable = false)
    private  Integer cutOff;

   @Column(name = "negative_marking",nullable = false)
    private  Integer negativeMarking;


   private String status;



   private Integer skipped;


   private String time;

   private  Double percent;
//
//   @Transient
////    @Column(name = "name",nullable = false)
//    private String name;
//
//

    @ManyToOne
    @JoinColumn(name = "stud_id",updatable = false,insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    Student student;

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

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Long totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public Integer getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(Integer correctAns) {
        this.correctAns = correctAns;
    }

    public Integer getWrongAns() {
        return wrongAns;
    }

    public void setWrongAns(Integer wrongAns) {
        this.wrongAns = wrongAns;
    }

    public Integer getCutOff() {
        return cutOff;
    }

    public void setCutOff(Integer cutOff) {
        this.cutOff = cutOff;
    }

    public Integer getNegativeMarking() {
        return negativeMarking;
    }

    public void setNegativeMarking(Integer negativeMarking) {
        this.negativeMarking = negativeMarking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSkipped() {
        return skipped;
    }

    public void setSkipped(Integer skipped) {
        this.skipped = skipped;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
