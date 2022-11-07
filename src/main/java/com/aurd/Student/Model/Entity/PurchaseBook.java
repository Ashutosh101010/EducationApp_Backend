package com.aurd.Student.Model.Entity;


import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "purchase_books")
public class PurchaseBook {

    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "book_id")
    private Long bookid;

    @Column(name = "stud_id")
    private Long studId;
    private String phone;

    @Column(name = "purchase_amount")
    private Integer purchaseAmount;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "discounted_amount")
    private Long discountedAmount;
    private String method;
    private String details;
    private String address;

    @Column(name = "delivery_status")
    private String delivery_status;

    @Column(name = "trans_id")
    private String transId;


    private Timestamp timestamp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
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

    public Integer getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Integer purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
