package com.aurd.Student.Model;

import com.aurd.Student.Model.Entity.StudentModel;
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
    private int id;

    private String description;

    private String pic;
    @Null
    @Nullable
    private int post_status;
    @Null
    @Nullable
    private Integer post_approved_by;

    @Null
    @Nullable
    private Timestamp post_approved_on;
    @Null
    @Nullable
    private int added_by;
    //    private Timestamp added_on;
    private int inst_id;
    private Timestamp added_on;



    @Null
    @Nullable
    private Timestamp 	updated_on;


    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "added_by", insertable = false, updatable = false)
    private StudentModel students;

    @Transient
    private String fname;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Timestamp updated_on) {
        this.updated_on = updated_on;
    }

    //    private Timestamp updated_on;

    public StudentModel getStudents() {
        return students;
    }

    public void setStudents(StudentModel students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getPost_status() {
        return post_status;
    }

    public void setPost_status(int post_status) {
        this.post_status = post_status;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public Integer getPost_approved_by() {
        return post_approved_by;
    }

    public void setPost_approved_by(Integer post_approved_by) {
        this.post_approved_by = post_approved_by;
    }

    public Timestamp getPost_approved_on() {
        return post_approved_on;
    }

    public void setPost_approved_on(Timestamp post_approved_on) {
        this.post_approved_on = post_approved_on;
    }


}
