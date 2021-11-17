package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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


public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="fname", nullable = false)
    @NotBlank
    @Size(max = 256)
    private String fname;

    @Column(name="lname" , nullable = true)
    @Size(max = 256)
    private String lastName;


    @Column(name="address", nullable = true)
    @Size(max = 256)
    private String address;

    @Column(name="username", nullable = false)
    @NotBlank
    @Size(max = 256)
    private String username;

    @Column(name="password", nullable = false)
    @NotBlank
    @Size(max = 256)
    private String password;


    @Column(name="email", nullable = false)
    @NotBlank
    private String email;

    @Column(name="contact", nullable = false)
    @NotBlank
    private String contact;

    @Column(name="profile", nullable = true)
    @Max(256)
    private String profile;


    @Column(name="dob", nullable = false)
    private Date dob;

    @Column(name="gender", nullable = true)
    private String gender;

    @Column(name="townId", nullable = true)
    @Null
    @Nullable
    private Integer townId;

    @Column(name="stateId", nullable = true)
    @Null
    @Nullable
    private Integer stateId;

    @Column(name="districtId", nullable = true)
    @Size(max = 50)
    @Null
    @Nullable
    private String districtId;

    @Column(name="reg_status", nullable = false)
    @NotBlank
    private String reg_status;

    @Column(name="email_verify", nullable = false)
    private int email_verify;

    @Column(name="phone_verify", nullable = false)
    private int phone_verify;

    @Column(name="inst_id", nullable = false)
    private int inst_id;

    @Column(name="login_status", nullable = false)
    private int login_status;


    @Column(name="library", nullable = false)
    private int library;

    @Column(name="hostel", nullable = false)
    private int hostel;

    @Column(name="coursecheck", nullable = false)
    private int coursecheck;

    @Column(name="password_salt", nullable = false)
    private String password_salt;

    @Column(name = "bio",nullable = true)
    private  String bio;

    @Column(name = "created_at",nullable = true)
    private Timestamp created_at;

    @Column(name = "deviceId",nullable = true)
    String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        this.dob=dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getReg_status() {
        return reg_status;
    }

    public void setReg_status(String reg_status) {
        this.reg_status = reg_status;
    }

    public int getEmail_verify() {
        return email_verify;
    }

    public void setEmail_verify(int email_verify) {
        this.email_verify = email_verify;
    }

    public int getPhone_verify() {
        return phone_verify;
    }

    public void setPhone_verify(int phone_verify) {
        this.phone_verify = phone_verify;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getLogin_status() {
        return login_status;
    }

    public void setLogin_status(int login_status) {
        this.login_status = login_status;
    }

    public int getLibrary() {
        return library;
    }

    public void setLibrary(int library) {
        this.library = library;
    }

    public int getHostel() {
        return hostel;
    }

    public void setHostel(int hostel) {
        this.hostel = hostel;
    }

    public int getCoursecheck() {
        return coursecheck;
    }

    public void setCoursecheck(int coursecheck) {
        this.coursecheck = coursecheck;
    }

    public String getPassword_salt() {
        return password_salt;
    }

    public void setPassword_salt(String password_salt) {
        this.password_salt = password_salt;
    }
}
