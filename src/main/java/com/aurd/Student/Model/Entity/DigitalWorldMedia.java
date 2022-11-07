package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "digital_world_media")
public class DigitalWorldMedia {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "folder_id")
    private Long folderId;

    private String media;
    private String type;

    @Column(name = "inst_id")
    private Long instId;

    private String name;

    @ManyToOne
   
    Institute institute;

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
