package com.aurd.Student.Model.Entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.smallrye.common.constraint.Nullable;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "student_posts")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StudentPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String pic;

    @Column(name = "post_status")
    private Boolean postStatus;

    @Column(name = "post_approved_by")
    private Long postApprovedBy;


    @Column(name = "post_approved_on")
    private Timestamp postApprovedOn;


    @Column(name = "added_by")
    private Long addedBy;


    @Column(name = "inst_id")
    private int instId;

    @Column(name = "added_on")
    private Timestamp addedOn;


    @Column(name = "updated_on")
    private Timestamp updatedOn;


    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "added_by", insertable = false, updatable = false)
    private Student student;

    @Transient
    private String fname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Boolean getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Boolean postStatus) {
        this.postStatus = postStatus;
    }

    public Long getPostApprovedBy() {
        return postApprovedBy;
    }

    public void setPostApprovedBy(Long postApprovedBy) {
        this.postApprovedBy = postApprovedBy;
    }

    public Timestamp getPostApprovedOn() {
        return postApprovedOn;
    }

    public void setPostApprovedOn(Timestamp postApprovedOn) {
        this.postApprovedOn = postApprovedOn;
    }

    public Long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Long addedBy) {
        this.addedBy = addedBy;
    }

    public int getInstId() {
        return instId;
    }

    public void setInstId(int instId) {
        this.instId = instId;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
