package com.aurd.Student.Model.Request;

import java.sql.Timestamp;

public class AddNotesRequest
{
    String title;
    String note;
    long stud_id;
    long inst_id;
    Long vid_id;
    Timestamp added_on;
    Timestamp updated_on;


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

    public Long getVid_id() {
        return vid_id;
    }

    public void setVid_id(Long vid_id) {
        this.vid_id = vid_id;
    }
}
