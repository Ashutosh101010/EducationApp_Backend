package com.aurd.Student.Model.Entity;

import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "quiz_master")
public class QuizModel {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quiz_id;

    @Column(name = "subject_id",nullable = true)
    private Integer subject_id;

    @Column(name = "sub_subject_id",nullable = true)

    private Integer sub_subject_id;


    @Column(name = "course_id",nullable = false)
    private Integer course_id;


    @Column(name = "type",nullable = false)
    @NotBlank
    private String type;

    @Column(name = "title",nullable = false)
    @NotBlank
    @Max(100)
    private String title;

    @Column(name = "discription",nullable = false)
    @NotBlank
    @Max(2000)
    private  String discription;


    @Column(name = "pic",nullable = true)
    @Null
    @Nullable
    private String pic;

    @Column(name = "price",nullable = false)
    private int price;

    @Column(name = "test_start",nullable = false)
    private Timestamp test_start;

//    @Column(name = "test_end",nullable = false)
//    private Timestamp test_end;

    @Column(name = "test_duration",nullable = true)
    private String test_duration;

    @Column(name = "added_on",nullable = false)
    private Timestamp added_on;

    @Column(name = "added_by",nullable = false)
    private long added_by;

    @Column(name = "updated_by",nullable = false)
    private long updated_by;

    @Column(name = "inst_id",nullable = true)
    @Null
    @Nullable
    private Long inst_id;


    @Column(name = "is_active",nullable = false)
    private  int is_active;

    @Column(name = "marks_per_ques",nullable = false)
    private long marks_per_ques;

    @Column(name = "total_ques",nullable = false)
    private  long total_ques;


    @Column(name = "cutoff",nullable = true)
    @Null
    @Nullable
    private Long cutoff;

    @Column(name = "instruction",nullable = true)
    @Max(429496729)
    private String instruction;

//
//    @Column(name = "time",nullable = false)
//    private Long time;


    public long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(long quiz_id) {
        this.quiz_id = quiz_id;
    }


    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public Integer getSub_subject_id() {
        return sub_subject_id;
    }

    public void setSub_subject_id(Integer sub_subject_id) {
        this.sub_subject_id = sub_subject_id;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getTest_start() {
        return test_start;
    }

    public void setTest_start(Timestamp test_start) {
        this.test_start = test_start;
    }


    public String getTest_duration() {
        return test_duration;
    }

    public void setTest_duration(String test_duration) {
        this.test_duration = test_duration;
    }

    public long getAdded_by() {
        return added_by;
    }

    public void setAdded_by(long added_by) {
        this.added_by = added_by;
    }

    public long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(long updated_by) {
        this.updated_by = updated_by;
    }

    public Long getInst_id() {
        return inst_id;
    }

    public void setInst_id(Long inst_id) {
        this.inst_id = inst_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public long getMarks_per_ques() {
        return marks_per_ques;
    }

    public void setMarks_per_ques(long marks_per_ques) {
        this.marks_per_ques = marks_per_ques;
    }

    public long getTotal_ques() {
        return total_ques;
    }

    public void setTotal_ques(long total_ques) {
        this.total_ques = total_ques;
    }

    public Long getCutoff() {
        return cutoff;
    }

    public void setCutoff(Long cutoff) {
        this.cutoff = cutoff;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }




}
