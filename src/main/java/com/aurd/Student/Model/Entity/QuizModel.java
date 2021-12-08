package com.aurd.Student.Model.Entity;

import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "quiz_master")
public class QuizModel {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quiz_id;

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
    private String price;

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
    private Integer inst_id;


    @Column(name = "is_active",nullable = false)
    private  int is_active;

    @Column(name = "marks_per_ques",nullable = true)
    @Null
    @Nullable
    private Integer marks_per_ques;

    @Column(name = "total_ques",nullable = false)
    private  Integer total_ques;


    @Column(name = "cutoff",nullable = true)
    @Null
    @Nullable
    private Long cutoff;

    @Column(name = "instruction",nullable = true)
    @Max(429496729)
    private String instruction;

    @Column(name = "time",nullable = false)
    private Timestamp time;

    @Column(name = "negative_marking",nullable = true)
    private String negative_marking;

    @Column(name = "quiz_type",nullable = true)
    private String quiz_type;

    @Column(name = "test_end",nullable = true)
    private String test_end;


    @Transient
    boolean testSeriesPurchased;

    public boolean isTestSeriesPurchased() {
        return testSeriesPurchased;
    }

    public void setTestSeriesPurchased(boolean testSeriesPurchased) {
        this.testSeriesPurchased = testSeriesPurchased;
    }

    public String getTest_end() {
        return test_end;
    }

    public void setTest_end(String test_end) {
        this.test_end = test_end;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getNegative_marking() {
        return negative_marking;
    }

    public void setNegative_marking(String negative_marking) {
        this.negative_marking = negative_marking;
    }

    public String getQuiz_type() {
        return quiz_type;
    }

    public void setQuiz_type(String quiz_type) {
        this.quiz_type = quiz_type;
    }

    //
//    @Column(name = "time",nullable = false)
//    private Long time;


    public Long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Long quiz_id) {
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

    public Integer getInst_id() {
        return inst_id;
    }

    public void setInst_id(Integer inst_id) {
        this.inst_id = inst_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public Integer getMarks_per_ques() {
        return marks_per_ques;
    }

    public void setMarks_per_ques(Integer marks_per_ques) {
        this.marks_per_ques = marks_per_ques;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getTotal_ques() {
        return total_ques;
    }

    public void setTotal_ques(Integer total_ques) {
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
