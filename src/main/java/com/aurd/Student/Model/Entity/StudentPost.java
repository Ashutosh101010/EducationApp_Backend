package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.common.constraint.Nullable;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "student_posts")
public class StudentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String description;

    private String pic;

    @Column(name = "post_status",nullable = false)
    private Boolean postStatus;

    @Column(name = "added_by",nullable = false)
    private  Integer addedBy;

    @Column(name = "added_on",nullable = false)
    private Timestamp addedOn;


    @Column(name = "inst_id",nullable = false)
    private  Long instId;

    @Column(name = "post_approved_by",nullable = true)
    private Long postApprovedBy;

   @Column(name = "post_approved_on",nullable = true)
   private Timestamp postApprovedOn;


    @ManyToOne
    @JoinColumn(name = "added_by",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    Student studentModel;

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

    public Integer getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
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

    public Student getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(Student studentModel) {
        this.studentModel = studentModel;
    }
}
