package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {


    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }


    private String test_type;
    private String title;
    private String price;
    private String teachers_name;
    private String sample_pdf;
    private Timestamp added_on;
    private Integer added_by;
    private Integer updated_by;
    private Integer inst_id;
    private Integer is_active;
    private Timestamp time;
    private Timestamp updated_on;
    private String discount_price;
    private String enroll_student;
    private String discounted_price;

    @OneToMany
    private List<BooksMedia> media;


    @OneToMany(mappedBy = "book_id")
    public List<BooksMedia> getMedia() {
        return media;
    }


    public void setMedia(List<BooksMedia> media) {
        this.media = media;
    }

    public String getTest_type() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
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

    public String getTeachers_name() {
        return teachers_name;
    }

    public void setTeachers_name(String teachers_name) {
        this.teachers_name = teachers_name;
    }

    public String getSample_pdf() {
        return sample_pdf;
    }

    public void setSample_pdf(String sample_pdf) {
        this.sample_pdf = sample_pdf;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public Integer getAdded_by() {
        return added_by;
    }

    public void setAdded_by(Integer added_by) {
        this.added_by = added_by;
    }

    public Integer getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Integer updated_by) {
        this.updated_by = updated_by;
    }

    public Integer getInst_id() {
        return inst_id;
    }

    public void setInst_id(Integer inst_id) {
        this.inst_id = inst_id;
    }

    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Timestamp updated_on) {
        this.updated_on = updated_on;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getEnroll_student() {
        return enroll_student;
    }

    public void setEnroll_student(String enroll_student) {
        this.enroll_student = enroll_student;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(String discounted_price) {
        this.discounted_price = discounted_price;
    }
}
