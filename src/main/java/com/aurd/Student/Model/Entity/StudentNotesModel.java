package com.aurd.Student.Model.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "student_notes")
public class StudentNotesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "note",nullable = false)
    @NotBlank
    private String note;

    @Column(name = "stud_id",nullable = false)
    private long stud_id;

    @Column(name = "inst_id",nullable = false)
    private long inst_id;

    @Column(name ="updated_on",nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updated_on;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp added_on;

    @Column(name = "vid_id",nullable = true)
    @Null
    @Nullable
    private Long vid_id;


    @Column(name = "title", nullable = false)
    @NotBlank
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getStud_id() {
        return stud_id;
    }

    public void setStud_id(long stud_id) {
        this.stud_id = stud_id;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    public Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Timestamp updated_on) {
        this.updated_on = updated_on;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public Long getVid_id() {
        return vid_id;
    }

    public void setVid_id(Long vid_id) {
        this.vid_id = vid_id;
    }
}
