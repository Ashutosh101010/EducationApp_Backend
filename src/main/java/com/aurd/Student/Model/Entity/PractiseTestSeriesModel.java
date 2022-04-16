package com.aurd.Student.Model.Entity;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "test_series_course")
public class PractiseTestSeriesModel {
    @Id
    private int id;



    @Column(name = "course_name",nullable = false)
    private  String courseName;

    @Column(name = "test_type",nullable = true)
    private  String testType;

    @Column(name = "price",nullable = true)
    private  Integer price;

    @Column(name = "image",nullable = true)

    private  String image;

    @Column(name = "num_of_quis",nullable = true)
    private  Integer numOfQuiz;

    @Column(name = "languages",nullable = false)
    private  String languages;

    @Column(name = "series_id",nullable = true)
    private  String seriesId;

    @Column(name = "duration",nullable = false)
    private String duration;

    @Column(name = "status",nullable = false)
    private Integer status;

    @Column(name = "created_at",nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at",nullable = false)
    private String updated_at;

    @Column(name = "marks_per_ques",nullable = false)
    private String marks_per_ques;

    @Column(name = "negative_marking",nullable = false)
    private  String  negative_marking;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getNumOfQuiz() {
        return numOfQuiz;
    }

    public void setNumOfQuiz(Integer numOfQuiz) {
        this.numOfQuiz = numOfQuiz;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getMarks_per_ques() {
        return marks_per_ques;
    }

    public void setMarks_per_ques(String marks_per_ques) {
        this.marks_per_ques = marks_per_ques;
    }

    public String getNegative_marking() {
        return negative_marking;
    }

    public void setNegative_marking(String negative_marking) {
        this.negative_marking = negative_marking;
    }
}
