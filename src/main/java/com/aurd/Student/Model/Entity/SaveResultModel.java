package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "result")

public class SaveResultModel {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stud_id",nullable = false)
    private long stud_id;

    @Column(name = "inst_id",nullable = false)
    private long inst_id;

    @Column(name = "quiz_id",nullable = false)
    private  long quiz_id;


    @Column(name = "total_marks",nullable = false)
    private long total_marks;

    @Column(name = "marks_obtained",nullable = false)
    private  int marks_obtained;

    @Column(name = "correct_ans",nullable = false)
    private  long correct_ans;

    @Column(name = "wrong_ans",nullable = false)
    private  long wrong_ans;

    @Column(name = "cut_off",nullable = false)
    private  long cut_off;

   @Column(name = "negative_marking",nullable = false)
    private  int negative_marking;

   @Column(name = "status",nullable = false)
   private String status;

   @Column(name = "name",nullable = false)
   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStud_id() {
        return stud_id;
    }

    public void setStud_id(long stud_id) {
        this.stud_id = stud_id;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    public long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public void setMarks_obtained(int marks_obtained) {
        this.marks_obtained = marks_obtained;
    }

    public long getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(long total_marks) {
        this.total_marks = total_marks;
    }

    public long getMarks_obtained() {
        return marks_obtained;
    }

    public void setMarks_obtained(long marks_obtained) {
        this.marks_obtained = (int) marks_obtained;
    }

    public long getCorrect_ans() {
        return correct_ans;
    }

    public void setCorrect_ans(long correct_ans) {
        this.correct_ans = correct_ans;
    }


    public long getWrong_ans() {
        return wrong_ans;
    }

    public void setWrong_ans(long wrong_ans) {
        this.wrong_ans = wrong_ans;
    }

    public long getCut_off() {
        return cut_off;
    }

    public void setCut_off(long cut_off) {
        this.cut_off = cut_off;
    }

    public int getNegative_marking() {
        return negative_marking;
    }

    public void setNegative_marking(int negative_marking) {
        this.negative_marking = negative_marking;
    }
}
