package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "batches")
public class RunningBatches {

    @Id
    @GeneratedValue
    private Long id;

    private String batch;


    private Time starting;


    private Time ending;

    @Column(name = "number_of_students")
    private  Integer numberOfStudents;

    @Column(name = "starting_date",nullable = false)
    private Date startingDate;

    @Column(name = "closing_date",nullable = false)
    private Date closingDate;

    private  String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
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

    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
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
