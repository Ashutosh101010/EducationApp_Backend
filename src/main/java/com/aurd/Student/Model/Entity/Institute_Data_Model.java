//package com.aurd.Student.Model.Entity;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//@Entity
//@Table(name = "institute_data")
//
//public class Institute_Data_Model {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name="clip", nullable = false)
//    @NotBlank
//    @Size(max = 256)
//    private String clip;
//
//    @Column(name = "course",nullable = false)
//    private  String course;
//
//
//    @Column(name="facebook",nullable = false)
//    @NotBlank
//    private String  facebook;
//
//
//    @Column(name="quote",nullable = true)
//    private  String quote;
//
//
//    @Column(name="instagram",nullable = false)
//    @NotBlank
//    @Size(max = 250)
//    private  String instagram;
//
//    @Column(name="about",nullable = true)
//    private  String about;
//
//    @Column(name = "offer",nullable = true)
//    private String offer;
//
//
//    @Column(name = "awards",nullable = true)
//    private String awards;
//
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getClip() {
//        return clip;
//    }
//
//    public void setClip(String clip) {
//        this.clip = clip;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    public String getFacebook() {
//        return facebook;
//    }
//
//    public void setFacebook(String facebook) {
//        this.facebook = facebook;
//    }
//
//    public String getQuote() {
//        return quote;
//    }
//
//    public void setQuote(String quote) {
//        this.quote = quote;
//    }
//
//    public String getInstagram() {
//        return instagram;
//    }
//
//    public void setInstagram(String instagram) {
//        this.instagram = instagram;
//    }
//
//    public String getAbout() {
//        return about;
//    }
//
//    public void setAbout(String about) {
//        this.about = about;
//    }
//
//    public String getOffer() {
//        return offer;
//    }
//
//    public void setOffer(String offer) {
//        this.offer = offer;
//    }
//
//    public String getAwards() {
//        return awards;
//    }
//
//    public void setAwards(String awards) {
//        this.awards = awards;
//    }
//
//}
