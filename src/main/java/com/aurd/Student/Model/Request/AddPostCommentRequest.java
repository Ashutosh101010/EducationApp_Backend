package com.aurd.Student.Model.Request;

import java.sql.Timestamp;

public class AddPostCommentRequest {


    String comment;
    int post_id;
    long added_by;
    Timestamp added_on;
    String type;
//    String stud_name;
//
//
//    public String getStud_name() {
//        return stud_name;
//    }
//
//    public void setStud_name(String stud_name) {
//        this.stud_name = stud_name;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public long getAdded_by() {
        return added_by;
    }

    public void setAdded_by(long added_by) {
        this.added_by = added_by;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }
}
