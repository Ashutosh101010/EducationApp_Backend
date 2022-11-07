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

public class CurrentAffairsComment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    @Column(name = "current_affair_id",nullable = false)
    private Long currentAffairId;

    @Column(name = "added_on",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp addedOn;

    @Column(name = "added_by",nullable = false)
    private Long addedBy;

    private String type;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCurrentAffairId() {
        return currentAffairId;
    }

    public void setCurrentAffairId(Long currentAffairId) {
        this.currentAffairId = currentAffairId;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }

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

}
