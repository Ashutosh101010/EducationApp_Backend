package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.runtime.annotations.IgnoreProperty;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "quiz_resp_stud")
public class Quiz_Submit_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;


    @Column(name = "stud_id ",nullable = false)
    private  long stud_id;

    @Column(name = "quiz_id",nullable = false)
    private long quiz_id;

    @Column(name = "ques_id",nullable = false)
    private long ques_id;

    @Column(name = "ans",nullable = true)
    private String ans;

    @Column(name = "marks_ob",nullable = false)
    private  int marks_ob;

    @Column(name = "is_visited",nullable = false)
    private  int is_visited;

    @Column(name = "is_ansered",nullable = false)
    private  int is_ansered;

    @Column(name = "is_marked",nullable = false)
    private int is_marked;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp added_on;

    @Column(name = "inst_id",nullable = false)
    private  int inst_id;

    @Column(name = "markForReview",nullable = false)
    private  int markForReview;

    public int getMarkForReview() {
        return markForReview;
    }

    public void setMarkForReview(int markForReview) {
        this.markForReview = markForReview;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStud_id() {
        return stud_id;
    }

    public void setStud_id(long stud_id) {
        this.stud_id = stud_id;
    }

    public long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public long getQues_id() {
        return ques_id;
    }

    public void setQues_id(long ques_id) {
        this.ques_id = ques_id;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public int getMarks_ob() {
        return marks_ob;
    }

    public void setMarks_ob(int marks_ob) {
        this.marks_ob = marks_ob;
    }

    public int getIs_visited() {
        return is_visited;
    }

    public void setIs_visited(int is_visited) {
        this.is_visited = is_visited;
    }

    public int getIs_ansered() {
        return is_ansered;
    }

    public void setIs_ansered(int is_ansered) {
        this.is_ansered = is_ansered;
    }

    public int getIs_marked() {
        return is_marked;
    }

    public void setIs_marked(int is_marked) {
        this.is_marked = is_marked;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }
}
