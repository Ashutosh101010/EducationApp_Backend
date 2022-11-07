package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "students")


public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fname", nullable = false)
    private String fname;

    @Column(name="lname" , nullable = true)
    private String lastName;


    private String address;

    private String username;


    @JsonIgnore
    private String password;



    private String email;


    private String contact;


    private String profile;


    private Date dob;


    private String gender;


    @Column(name="state_id", nullable = true)
    private Long stateId;

    @Column(name="city_id", nullable = true)
    private Integer cityId;



    @Column(name="reg_status", nullable = false)
    @NotBlank
    private String reg_status;

    @Column(name="email_verify", nullable = false)
    private Boolean email_verify;

    @Column(name="phone_verify", nullable = false)
    private Boolean phone_verify;

    @Column(name="inst_id", nullable = false)
    private Long instId;

    @Column(name="login_status", nullable = false)
    private Boolean login_status;


    @Column(name="library", nullable = false)
    private Boolean library;

    @Column(name="hostel", nullable = false)
    private Boolean hostel;

    @Column(name="course_check", nullable = false)
    private Boolean courseCheck;

    @Column(name="password_salt", nullable = false)
    @JsonIgnore
    private String passwordSalt;

    private  String bio;

    @Column(name = "created_at",nullable = true)
    private Timestamp createdAt;

    @Column(name = "device_id",nullable = true)
    private String deviceId;

    @Column(name = "is_active",nullable = false)
    private Boolean is_active;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getReg_status() {
        return reg_status;
    }

    public void setReg_status(String reg_status) {
        this.reg_status = reg_status;
    }

    public Boolean getEmail_verify() {
        return email_verify;
    }

    public void setEmail_verify(Boolean email_verify) {
        this.email_verify = email_verify;
    }

    public Boolean getPhone_verify() {
        return phone_verify;
    }

    public void setPhone_verify(Boolean phone_verify) {
        this.phone_verify = phone_verify;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Boolean getLogin_status() {
        return login_status;
    }

    public void setLogin_status(Boolean login_status) {
        this.login_status = login_status;
    }

    public Boolean getLibrary() {
        return library;
    }

    public void setLibrary(Boolean library) {
        this.library = library;
    }

    public Boolean getHostel() {
        return hostel;
    }

    public void setHostel(Boolean hostel) {
        this.hostel = hostel;
    }

    public Boolean getCourseCheck() {
        return courseCheck;
    }

    public void setCourseCheck(Boolean courseCheck) {
        this.courseCheck = courseCheck;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}
