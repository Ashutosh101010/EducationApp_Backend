package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "purchase_testseries")


public class PurchaseTest {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "stud_id")
    private  Long studId;

    @Column(name = "test_series_id")
    private Long testSeriesId;

    @Column(name = "purchase_amount")
    private  Long purchaseAmount;

    @Column(name = "created_on")
    private Timestamp createdOn;

    private String phone;


    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "discounted_amount")
    private Long discountedAmount;
//    private Long discount;
    private String method;
    private String details;

    @Column(name = "trans_id")
    private String transId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudId() {
        return studId;
    }

    public void setStudId(Long studId) {
        this.studId = studId;
    }

    public Long getTestSeriesId() {
        return testSeriesId;
    }

    public void setTestSeriesId(Long testSeriesId) {
        this.testSeriesId = testSeriesId;
    }

    public Long getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Long purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Long discountedAmount) {
        this.discountedAmount = discountedAmount;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
}
