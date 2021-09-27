package com.aurd.Student.Model.Entity;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "test_series_practice_test")
public class PractiseTestSeriesModel {
    @Id

    @Column(name = "test_series_id",nullable = false)
    private  int test_series_id;

    @Column(name = "topic_id",nullable = true)
    @Nullable
    @Null
    private  Integer topic_id;

    @Column(name = "subject_id",nullable = true)
    @Nullable
    @Null
    private  Integer subject_id;

    @Column(name = "sub_subject_id",nullable = true)
    @Nullable
    @Null
    private  Integer getSubject_id;

    @Column(name = "course_id",nullable = true)
    @Nullable
    @Null
    private  Integer course_id;

    @Column(name = "title",nullable = false)
    private  String title;

    @Column(name = "discription",nullable = true)
    private  String discription;

    @Column(name = "pic",nullable = false)
    private String pic;

    @Column(name = "price",nullable = false)
    private String price;

    @Column(name = "test_start",nullable = false)
    private Timestamp test_start;

    @Column(name = "test_duration",nullable = false)
    private String test_duration;

    @Column(name = "added_on",nullable = false)
    private Timestamp added_on;

    @Column(name = "added_by",nullable = false)
    private  int added_by;

    @Column(name = "updated_by",nullable = false)
    private  int updated_by;

    @Column(name = "inst_id",nullable = true)
    @Nullable
    @Null
    private  Integer inst_id;

    @Column(name = "is_active",nullable = false)
    private  int is_active;

    @Column(name = "marks_per_ques",nullable = true)
    private Integer marks_per_ques;

    @Column(name = "total_ques",nullable = true)
    private  Integer total_ques;

    @Column(name = "cutoff",nullable = true)
    private  int cutoff;

    @Column(name="instruction",nullable = false)
    private String instruction;

    @Column(name = "time",nullable = false)
    private  Timestamp 	time;

    @Column(name = "negative_marking",nullable = true)
    private  String negative_marking;

    @Column(name = "code",nullable = false)
    private  String code;

    @Column(name = "test_end",nullable = false)
    private String test_end;


    public int getTest_series_id() {
        return test_series_id;
    }

    public void setTest_series_id(int test_series_id) {
        this.test_series_id = test_series_id;
    }


    public Integer getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Integer topic_id) {
        this.topic_id = topic_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public Integer getGetSubject_id() {
        return getSubject_id;
    }

    public void setGetSubject_id(Integer getSubject_id) {
        this.getSubject_id = getSubject_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public int getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(int updated_by) {
        this.updated_by = updated_by;
    }


    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }


    public int getCutoff() {
        return cutoff;
    }

    public void setCutoff(int cutoff) {
        this.cutoff = cutoff;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTest_end() {
        return test_end;
    }

    public void setTest_end(String test_end) {
        this.test_end = test_end;
    }

    public Integer getInst_id() {
        return inst_id;
    }

    public void setInst_id(Integer inst_id) {
        this.inst_id = inst_id;
    }

    public Integer getMarks_per_ques() {
        return marks_per_ques;
    }

    public void setMarks_per_ques(Integer marks_per_ques) {
        this.marks_per_ques = marks_per_ques;
    }

    public Integer getTotal_ques() {
        return total_ques;
    }

    public void setTotal_ques(Integer total_ques) {
        this.total_ques = total_ques;
    }
}
