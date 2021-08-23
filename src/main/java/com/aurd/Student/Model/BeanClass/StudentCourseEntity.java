package com.aurd.Student.Model.BeanClass;

import java.sql.Date;
import java.sql.Timestamp;

public class StudentCourseEntity {
    long courseId;
    double actualFee;
    double discount;
    double courseFee;
    double paidFee;
    double remainingFee;
    Date last_paid;
    Date due_date;
    String reason;
    Timestamp created_at;
    String transaction_id;
    String course;
    String description;
    String logo;
    int duration;
    double fee;
    String banner_img;
    String language;

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public double getActualFee() {
        return actualFee;
    }

    public void setActualFee(double actualFee) {
        this.actualFee = actualFee;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public double getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(double paidFee) {
        this.paidFee = paidFee;
    }

    public double getRemainingFee() {
        return remainingFee;
    }

    public void setRemainingFee(double remainingFee) {
        this.remainingFee = remainingFee;
    }

    public Date getLast_paid() {
        return last_paid;
    }

    public void setLast_paid(Date last_paid) {
        this.last_paid = last_paid;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
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
}
