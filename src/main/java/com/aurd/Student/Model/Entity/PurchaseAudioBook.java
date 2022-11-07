package com.aurd.Student.Model.Entity;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "purchase_audiobooks")
@Entity
public class PurchaseAudioBook {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "audio_book_id")
    private Long audioBookId;

    @Column(name = "stud_id")
    private Long studId;
    private String phone;


    @Column(name = "purchase_amount")
    private Long purchaseAmount;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "discounted_amount")
    private Long discountedAmount;

    private String method;
    private String details;

    @Column(name = "trans_id")
    private String transId;

    @Column(name = "created_on")
    private Timestamp createdOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAudioBookId() {
        return audioBookId;
    }

    public void setAudioBookId(Long audioBookId) {
        this.audioBookId = audioBookId;
    }

    public Long getStudId() {
        return studId;
    }

    public void setStudId(Long studId) {
        this.studId = studId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Long purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
