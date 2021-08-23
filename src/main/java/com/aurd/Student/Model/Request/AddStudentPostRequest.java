package com.aurd.Student.Model.Request;

import java.sql.Timestamp;

public class AddStudentPostRequest {
    private String discription;
    private String pic;
    private int post_status;
    private  int added_by;
    private Timestamp added_on;
    private  int inst_id;
    private int post_approved_by;
    private Timestamp post_approved_on;

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPost_status() {
        return post_status;
    }

    public void setPost_status(int post_status) {
        this.post_status = post_status;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getPost_approved_by() {
        return post_approved_by;
    }

    public void setPost_approved_by(int post_approved_by) {
        this.post_approved_by = post_approved_by;
    }

    public Timestamp getPost_approved_on() {
        return post_approved_on;
    }

    public void setPost_approved_on(Timestamp post_approved_on) {
        this.post_approved_on = post_approved_on;
    }
}
