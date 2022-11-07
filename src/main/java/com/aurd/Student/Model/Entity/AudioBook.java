package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "audio_books")
public class AudioBook {
    @Id
    @GeneratedValue
    private Long id;
    @Transient
    private Boolean purchased;

    private String testType;
    private String title;
    private String pic;
    private Double price;
    private String introAudio;
    private Timestamp addedOn;
    private Long addedBy;
    private Long updatedBy;
    private Long instId;
    private Boolean isActive;
    private Timestamp time;
    private Timestamp updatedOn;
    private Double discountedPrice;
    private String enrollStudent;


    @ManyToOne
    Institute institute;

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }
    @Column(name = "test_type")
    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    @Column(name = "intro_audio")
    public String getIntroAudio() {
        return introAudio;
    }

    public void setIntroAudio(String introAudio) {
        this.introAudio = introAudio;
    }

    @Column(name = "added_on")
    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }

    @Column(name = "added_by")
    public Long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Long addedBy) {
        this.addedBy = addedBy;
    }

    @Column(name = "updated_by")
    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }


    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    @Column(name = "is_active")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Column(name = "updated_on")
    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "discounted_price")
    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Column(name = "enroll_student")
    public String getEnrollStudent() {
        return enrollStudent;
    }

    public void setEnrollStudent(String enrollStudent) {
        this.enrollStudent = enrollStudent;
    }

}
