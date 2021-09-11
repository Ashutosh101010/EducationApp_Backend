package com.aurd.Student.Model.Entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "enquiry")

public class EnquiryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    @NotBlank
    @Size(max = 256)
    private String name;

    @Column(name="email",nullable = false)
    @NotBlank
    private String email;


    @Column(name="contact",nullable = true)
    private  String contact;


    @Column(name="description",nullable = false)
    @NotBlank
    @Size(max = 250)
    private  String description;

    @Column(name="status",nullable = true)
    private  String status;

    @Column(name="address",nullable = true)
    private String address;


    @Column(name="reason",nullable = true)
    private String reason;


    @Column(name="course",nullable = false)
    @NotBlank
    private String course;

    @Column(name="reference",nullable = true)
    private String reference;

    @Column(name="demo",nullable = true)
    private String demo;


    @Column(name="classDate",nullable = true)
    private String classDate;

    @Column(name="classTime",nullable = true)
    private String classTime;


    @Column(name="classTo",nullable = true)
    private String classTo;

    @Column(name="reminder",nullable = false)
    private java.sql.Date reminder;

    @Column(name="created_at",nullable = false)
    private Timestamp created_at;

    @Column(name="updated_at",nullable = false)
    private  Timestamp updated_at;

    @Column(name="inst_id",nullable = false)
    private int inst_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassTo() {
        return classTo;
    }

    public void setClassTo(String classTo) {
        this.classTo = classTo;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }
}
