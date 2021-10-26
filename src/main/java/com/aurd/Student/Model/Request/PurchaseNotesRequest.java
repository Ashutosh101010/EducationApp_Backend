package com.aurd.Student.Model.Request;

import java.sql.Timestamp;

public class PurchaseNotesRequest {

    int id;
    int stud_id;
    int notes_id;
    long purchase_amount;
    Timestamp created_on;

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
        return notes_id;
    }

    public void setNotes_id(int notes_id) {
        this.notes_id = notes_id;
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
