package com.aurd.Student.Model.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "current_affairs_liked")

public class CurrentAffairsLiked {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "current_affair_id")
    private Long currentAffairId;

    @Column(name = "added_by")
    private Long addedBy;

    @Column(name = "added_on")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp addedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getCurrentAffairId() {
        return currentAffairId;
    }

    public void setCurrentAffairId(Long currentAffairId) {
        this.currentAffairId = currentAffairId;
    }

    public Long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Long addedBy) {
        this.addedBy = addedBy;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }
}
