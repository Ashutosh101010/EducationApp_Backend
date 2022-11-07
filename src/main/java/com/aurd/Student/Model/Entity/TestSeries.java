package com.aurd.Student.Model.Entity;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "testseries_course")
public class TestSeries {



    @Id
    @GeneratedValue
    private Long id;
    @Column(name="cut_off")
    private Integer cut_off;


    @Column(name = "course_name",nullable = false)
    private  String courseName;

    @Column(name = "test_type",nullable = true)
    private  String testType;

    private  Integer price;

    private  String image;

    @Column(name = "num_of_ques",nullable = true)
    private  Integer numOfQues;


    private  String languages;

    @Column(name = "series_id",nullable = true)
    private  String seriesId;

    private String duration;


    private Integer status;

    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at",nullable = false)
    private String updatedAt;

    @Column(name = "marks_per_ques",nullable = false)
    private String marksPerQues;

    @Column(name = "negative_marking",nullable = false)
    private  String  negativeMarking;

    private String test;



    @Transient
    private  Boolean attempt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCut_off() {
        return cut_off;
    }

    public void setCut_off(Integer cut_off) {
        this.cut_off = cut_off;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNumOfQues() {
        return numOfQues;
    }

    public void setNumOfQues(Integer numOfQues) {
        this.numOfQues = numOfQues;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMarksPerQues() {
        return marksPerQues;
    }

    public void setMarksPerQues(String marksPerQues) {
        this.marksPerQues = marksPerQues;
    }

    public String getNegativeMarking() {
        return negativeMarking;
    }

    public void setNegativeMarking(String negativeMarking) {
        this.negativeMarking = negativeMarking;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Boolean getAttempt() {
        return attempt;
    }

    public void setAttempt(Boolean attempt) {
        this.attempt = attempt;
    }
}
