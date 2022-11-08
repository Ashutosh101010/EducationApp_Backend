package com.aurd.Student.Model.Request;


import java.sql.Timestamp;

public class AddKeyNotesRequest {

    private String title;
    private Long instId;
    private Long studId;
    private Long liveClassId;
    private String category;
    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getStudId() {
        return studId;
    }

    public void setStudId(Long studId) {
        this.studId = studId;
    }

    public Long getLiveClassId() {
        return liveClassId;
    }

    public void setLiveClassId(Long liveClassId) {
        this.liveClassId = liveClassId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
