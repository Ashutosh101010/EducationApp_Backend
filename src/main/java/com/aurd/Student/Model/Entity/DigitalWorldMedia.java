package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "digital_world_media")
public class DigitalWorldMedia {
    private Long id;
    @Column(name = "folder_id")
    private Long folderId;

    private String media;
    private String type;
    private Long inst_id;





    public Long getInst_id() {
        return inst_id;
    }

    public void setInst_id(Long inst_id) {
        this.inst_id = inst_id;
    }


    @Column(name = "folder_id")
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

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
