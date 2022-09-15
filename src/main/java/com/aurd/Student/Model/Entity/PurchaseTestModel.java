package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "purchase_testseries")


public class PurchaseTestModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stud_id",nullable = false)
    private  int stud_id;

    @Column(name = "test_seriesId",nullable = false)
    private int test_seriesId;

    @Column(name = "purchase_amount",nullable = true)
    private  long purchase_amount;

    @Column(name = "created_on",nullable = true)
    private Timestamp created_on;

    private String phone;
    private Integer assigned_by;

    private Long total_amount;
    private Long discounted_amount;
//    private Long discount;
    private String method;
    private String details;
    private String trans_id;

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public int getTest_seriesId() {
        return test_seriesId;
    }

    public void setTest_seriesId(int test_seriesId) {
        this.test_seriesId = test_seriesId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAssigned_by() {
        return assigned_by;
    }

    public void setAssigned_by(Integer assigned_by) {
        this.assigned_by = assigned_by;
    }

    public Long getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Long total_amount) {
        this.total_amount = total_amount;
    }

    public Long getDiscounted_amount() {
        return discounted_amount;
    }

    public void setDiscounted_amount(Long discounted_amount) {
        this.discounted_amount = discounted_amount;
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




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public int getNotes_id() {
        return test_seriesId;
    }

    public void setNotes_id(int notes_id) {
        this.test_seriesId = notes_id;
    }

    public long getPurchase_amount() {
        return purchase_amount;
    }

    public void setPurchase_amount(long purchase_amount) {
        this.purchase_amount = purchase_amount;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }
}
