package com.aurd.Student.Model.Entity;

import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "courses")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="course", nullable = false)
    @NotBlank
    @Size(max = 256)
    private String course;


    @Column(name="description", nullable = false)
    @NotBlank
    @Size(max = 256)
    private String description;

    @Column(name="logo", nullable = false)
    @NotBlank
    @Size(max = 256)
    private String logo;

    @Column(name="duration", nullable = false)
    private int duration;


    @Column(name="fee", nullable = false)
    private double fee;

    @Column(name="runningBatch", nullable = true)
    @Nullable
    @Null
    private Integer runningBatch;

    @Column(name="domainId", nullable = true)
    @Nullable
    @Null
    private Integer domainId;

    @Column(name = "inst_id",nullable = false)
    private int inst_id;

    @Column(name = "course_active",nullable = false)
    private int course_active;

    @Column(name = "banner_img",nullable = true)
    @Nullable
    @Null
    @Max(101)
    private String banner_img;

    @Column(name = "language",nullable = true)
    @Nullable
    @Null
    @Max(101)
    private String language;

    @Column(name = "created_at",nullable = true)
    private Timestamp created_at;

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }



    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getCourse_active() {
        return course_active;
    }

    public void setCourse_active(int course_active) {
        this.course_active = course_active;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getRunningBatch() {
        return runningBatch;
    }

    public void setRunningBatch(Integer runningBatch) {
        this.runningBatch = runningBatch;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }
}
