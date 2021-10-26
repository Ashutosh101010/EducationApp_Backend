package com.aurd.Student.Model.Request;


import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.persistence.Transient;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.sql.Date;

public class UpDateRequest {

    @FormParam("pic")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream pic;

    @FormParam("name")
    @PartType(MediaType.TEXT_PLAIN)
    String f_name;

    @FormParam("mobileNumber")
    @PartType(MediaType.TEXT_PLAIN)
    String mobile_no;
    @FormParam("address")
    @PartType(MediaType.TEXT_PLAIN)
    String address;

    @FormParam("email")
    @PartType(MediaType.TEXT_PLAIN)
    String email;

    @FormParam("state_id")
    @PartType(MediaType.TEXT_PLAIN)
    Integer state_id;

    @FormParam("district_id")
    @PartType(MediaType.TEXT_PLAIN)
    String district_id;
    @FormParam("dob")
    @PartType(MediaType.TEXT_PLAIN)
    String DOB;
    @FormParam("stud_id")
    @PartType(MediaType.TEXT_PLAIN)
    Long student_id;
    @FormParam("bio")
    @PartType(MediaType.TEXT_PLAIN)
    String bio;
    @FormParam("gender")
    @PartType(MediaType.TEXT_PLAIN)
    String gender;


    @FormParam("image")
    @PartType(MediaType.TEXT_PLAIN)
    String image;




    @Transient
    String imageId;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public InputStream getPic() {
        return pic;
    }

    public void setPic(InputStream pic) {
        this.pic = pic;
    }
}
