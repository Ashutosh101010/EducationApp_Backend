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
