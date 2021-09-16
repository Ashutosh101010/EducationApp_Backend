package com.aurd.Student.Model.Request;

import java.sql.Timestamp;

public class CommentReplyRequest {

    int comment_id;
    int post_id;
    int user_id;
    Timestamp added_on;
    String comment_reply;
    String type;
    String fname;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public String getComment_reply() {
        return comment_reply;
    }

    public void setComment_reply(String comment_reply) {
        this.comment_reply = comment_reply;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
