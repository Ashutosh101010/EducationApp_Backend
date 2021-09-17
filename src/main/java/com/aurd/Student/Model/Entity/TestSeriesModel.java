package com.aurd.Student.Model.Entity;

import com.mysql.cj.exceptions.StreamingNotifiable;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "testseries")
public class TestSeriesModel {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "image",nullable = false)
    private String image;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "total",nullable = false)
    private long total;

    @Column(name = "pic",nullable = true)
    @Null
    @Nullable
    private String pic;

    @Column(name = "test_start",nullable = false)
    private Timestamp test_start;

    @Column(name = "test_end",nullable = true)
    private Timestamp test_end;

    @Column(name = "test_duration",nullable = true)
    private Long test_duration;


    @Column(name = "added_by",nullable = false)
    private long added_by;

    @Column(name = "inst_id",nullable = false)
    private long inst_id;


    @Column(name = "is_active",nullable = false)
    private  int is_active;


    @Column(name = "cutoff",nullable = true)
    private Long cutoff;

    @Column(name = "instruction",nullable = true)
    private String instruction;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "subject_id",nullable = false)
    private  int subject_id;


    @Column(name = "quiz_id",nullable = false)
    private  long quiz_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Timestamp getTest_start() {
        return test_start;
    }

    public void setTest_start(Timestamp test_start) {
        this.test_start = test_start;
    }

    public Timestamp getTest_end() {
        return test_end;
    }

    public void setTest_end(Timestamp test_end) {
        this.test_end = test_end;
    }

    public Long getTest_duration() {
        return test_duration;
    }

    public void setTest_duration(Long test_duration) {
        this.test_duration = test_duration;
    }

    public long getAdded_by() {
        return added_by;
    }

    public void setAdded_by(long added_by) {
        this.added_by = added_by;
    }

    public Long getInst_id() {
        return inst_id;
    }

    public void setInst_id(Long inst_id) {
        this.inst_id = inst_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public Long getCutoff() {
        return cutoff;
    }

    public void setCutoff(Long cutoff) {
        this.cutoff = cutoff;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(long quiz_id) {
        this.quiz_id = quiz_id;
    }
}
