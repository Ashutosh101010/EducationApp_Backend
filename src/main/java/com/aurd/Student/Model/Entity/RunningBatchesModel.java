package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "batches")
public class RunningBatchesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "batch",nullable = false)
    private String batch;

    @Column(name = "starting",nullable = false)
    private Time starting;

    @Column(name = "ending",nullable = false)
    private Time ending;

    @Column(name = "numberOfStudents",nullable = false)
    private  long numberOfStudents;

    @Column(name = "startingDate",nullable = false)
    private Date startingDate;

    @Column(name = "closingDate",nullable = false)
    private Date closingDate;

    @Column(name = "status", nullable = false)
    private  String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Time getStarting() {
        return starting;
    }

    public void setStarting(Time starting) {
        this.starting = starting;
    }

    public Time getEnding() {
        return ending;
    }

    public void setEnding(Time ending) {
        this.ending = ending;
    }

    public long getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(long numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
