package com.aurd.Student.Model.Request;

import java.sql.Date;

public class RegisterRequest {
    String fname;
    String lastName;
    String address;String username;String password;
    String email;String contact;String profile;
    Date dob; String gender;
    int townId;
    int stateId;
    String districtId;
    String reg_status;
    int email_verify;
    int phone_verify;
    int inst_id;
    int login_status;int library;int hostel; int coursecheck;String password_salt;


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

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
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
