package com.aurd.Student.Model.Entity;


import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "purchase_notes")

public class PurchaseNotes {

        @Id
        @GeneratedValue
        private Long id;

        @Column(name = "stud_id",nullable = false)
        private  Long studId;

        @Column(name = "notes_id",nullable = false)
       private Long notesId;

        @Column(name = "purchase_amount",nullable = true)
      private  Long purchaseAmount;

        @Column(name = "created_on",nullable = true)
       private Timestamp createdOn;

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

    public Long getNotesId() {
        return notesId;
    }

    public void setNotesId(Long notesId) {
        this.notesId = notesId;
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
}
