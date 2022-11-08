package com.aurd.Student.Model.Request;

import java.sql.Timestamp;

public class AddNotesRequest
{
    private String title;
    private String note;
    private Long studId;
    private Long instId;
    private Long vidId;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getStudId() {
        return studId;
    }

    public void setStudId(Long studId) {
        this.studId = studId;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getVidId() {
        return vidId;
    }

    public void setVidId(Long vidId) {
        this.vidId = vidId;
    }

//    public Timestamp getAddedOn() {
//        return addedOn;
//    }
//
//    public void setAddedOn(Timestamp addedOn) {
//        this.addedOn = addedOn;
//    }
//
//    public Timestamp getUpdatedOn() {
//        return updatedOn;
//    }
//
//    public void setUpdatedOn(Timestamp updatedOn) {
//        this.updatedOn = updatedOn;
//    }
}
