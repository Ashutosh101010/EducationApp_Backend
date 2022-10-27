package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "student_courses")
public class StudentCourseModel {

    @Id
    private int id;

    @Column(name = "userId",nullable = false)
   private long userId;

    @Column(name = "courseId",nullable = false)
    private Integer courseId;

    @Column(name = "actualFee",nullable = false)
    private double actualFee;

    @Column(name = "discount",nullable = false)
    private double discount;

    @Column(name = "courseFee",nullable = false)
    private double courseFee;

    @Column(name = "paidFee",nullable = false)
    private double paidFee;


    @Column(name = "remainingFee",nullable = false)
    private  double remainingFee;

    @Column (name = "last_paid",nullable = true)
    @Null
    @Nullable
    private Date last_paid;

    @Column(name = "due_date",nullable = false)
    private java.sql.Date due_date;

    @Column(name = "reason",nullable = true)
    @Null
    @Nullable
    private String reason;

    @Column(name = "created_at",nullable = true)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created_at;


    @Column(name="updated_at",nullable = true)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private  Timestamp updated_at;

    @Column(name = "transaction_id",nullable = true)
    private  String transaction_id;

    @Column(name = "inst_id",nullable = false)
    private long inst_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
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

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }


    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    @Override
    public String toString() {
        return "StudentCourseModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", actualFee=" + actualFee +
                ", discount=" + discount +
                ", courseFee=" + courseFee +
                ", paidFee=" + paidFee +
                ", remainingFee=" + remainingFee +
                ", last_paid=" + last_paid +
                ", due_date=" + due_date +
                ", reason='" + reason + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", transaction_id='" + transaction_id + '\'' +
                ", inst_id=" + inst_id +
                '}';
    }
}
