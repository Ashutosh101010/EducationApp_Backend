package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;


@Entity
@Table(name = "current_affairs_comments")

public class Current_AffairsCommented_Model {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @Column(name = "comment",nullable = false)
    private String comment;

    @Column(name = "current_affair_id",nullable = false)
    private int current_affair_id;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp added_on;

    @Column(name = "added_by",nullable = false)
    private int added_by;

    @Column(name = "type",nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "added_by",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    StudentModel studentModel;

    @ManyToOne
    @JoinColumn(name="added_by",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    TeacherModel teacherModel;

    public TeacherModel getTeacherModel() {
        return teacherModel;
    }

    public void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCurrent_affair_id() {
        return current_affair_id;
    }

    public void setCurrent_affair_id(int current_affair_id) {
        this.current_affair_id = current_affair_id;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }


}
