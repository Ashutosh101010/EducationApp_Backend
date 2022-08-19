package com.aurd.Student.Model.BeanClass;

import com.aurd.Student.Model.Entity.Comment_Reply_Model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CommentEntity {

    int comment_id;
    String comment;
    int post_id;
    Timestamp added_on;
    String fname;
    String image;

    ArrayList<Comment_Reply_Model> ReplyList = new ArrayList<>();
    int reply_id;
    Long user_id;
    String comment_reply;
    String type;
    String time;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public ArrayList<Comment_Reply_Model> getReplyList() {
        return ReplyList;
    }

    public void setReplyList(ArrayList<Comment_Reply_Model> replyList) {
        ReplyList = replyList;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
