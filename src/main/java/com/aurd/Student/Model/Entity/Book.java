package com.aurd.Student.Model.Entity;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {


    @Id
    @GeneratedValue
    private Long id;

    private String testType;
    private String title;
    private String price;
    private String samplePdf;
    private Timestamp addedOn;
    private Long addedBy;
    private Long updatedBy;
    private Long instId;
    private Boolean isActive;
    private Timestamp time;
    private Timestamp updatedOn;
    private Double discountedrice;


    @ManyToOne
   
    Institute institute;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Column(name = "sample_pdf")
    public String getSamplePdf() {
        return samplePdf;
    }

    public void setSamplePdf(String samplePdf) {
        this.samplePdf = samplePdf;
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

    @Column(name = "inst_id")
    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Boolean getActive() {
        return isActive;
    }

    @Column(name = "is_active")
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
    public Double getDiscountedrice() {
        return discountedrice;
    }

    public void setDiscountedrice(Double discountedrice) {
        this.discountedrice = discountedrice;
    }

    @OneToMany
    private List<BooksMedia> media;


    @OneToMany(mappedBy = "book_id")
    public List<BooksMedia> getMedia() {
        return media;
    }


    public void setMedia(List<BooksMedia> media) {
        this.media = media;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
