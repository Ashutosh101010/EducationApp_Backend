package com.aurd.Student.Model.Request;

import java.sql.Timestamp;

public class PurchaseTestSeriesRequest {

    int id;
    int test_seriesId;
    int stud_id;
    Timestamp created_on;
    long purchase_amount;

    private String phone;

    private String transactionId;

    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTest_seriesId() {
        return test_seriesId;
    }

    public void setTest_seriesId(int test_seriesId) {
        this.test_seriesId = test_seriesId;
    }

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }

    public long getPurchase_amount() {
        return purchase_amount;
    }

    public void setPurchase_amount(long purchase_amount) {
        this.purchase_amount = purchase_amount;
    }
}
