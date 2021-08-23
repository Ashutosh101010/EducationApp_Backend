package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "sub_subjects")
public class SubSubjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sub_subject",nullable = false)
    @Max(256)
    @NotBlank
    private String sub_subject;

    @Column(name = "inst_id",nullable = false)
    private int inst_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSub_subject() {
        return sub_subject;
    }

    public void setSub_subject(String sub_subject) {
        this.sub_subject = sub_subject;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }
}
