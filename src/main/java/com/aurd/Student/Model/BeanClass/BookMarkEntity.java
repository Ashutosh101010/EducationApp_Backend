package com.aurd.Student.Model.BeanClass;

import java.sql.Timestamp;

public class BookMarkEntity {
    private long id;
    private Timestamp added_on;
    private long added_by;
    private String discription;
    private  String pic;
    private int post_status;
    private String name;
    private Long commentCount;

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public long getAdded_by() {
        return added_by;
    }

    public void setAdded_by(long added_by) {
        this.added_by = added_by;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
