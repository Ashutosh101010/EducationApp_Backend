package com.aurd.Student.Model.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "blog_comment")

public class BlogComment {



    private Long id;
    private String comment;


    private Long blogId;


    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp addedOn;


    private Long addedBy;


    private String type;

//    @ManyToOne
//    @JoinColumn(name = "added_by",insertable = false,updatable = false)
//    private StudentModel studentModel;



//    @ManyToOne
//    @JoinColumn(name="added_by",insertable = false,updatable = false)
//    private Employee employee;



//    @ManyToOne
//    @JoinColumn(name = "blog_id",insertable = false,updatable = false)
//    private Blog blog;



    public void setId(Long id) {
        this.id = id;
    }
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "blog_id")
    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public StudentModel getStudentModel() {
//        return studentModel;
//    }
//
//    public void setStudentModel(StudentModel studentModel) {
//        this.studentModel = studentModel;
//    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

//    public Blog getBlog() {
//        return blog;
//    }
//
//    public void setBlog(Blog blog) {
//        this.blog = blog;
//    }
}







