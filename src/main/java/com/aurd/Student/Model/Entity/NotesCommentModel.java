package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity(name = "notes_comment")
public class NotesCommentModel {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @Column(name = "comment",nullable = false)
    private String comment;

    @Column(name = "notes_id",nullable = false)
    private int notes_id;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp added_on;

    @Column(name = "added_by",nullable = false)
    private Long added_by;


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

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    public TeacherModel getTeacherModel() {
        return teacherModel;
    }

    public void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
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

    public int getNotes_id() {
        return notes_id;
    }

    public void setNotes_id(int notes_id) {
        this.notes_id = notes_id;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public Long getAdded_by() {
        return added_by;
    }

    public void setAdded_by(Long added_by) {
        this.added_by = added_by;
    }
}
