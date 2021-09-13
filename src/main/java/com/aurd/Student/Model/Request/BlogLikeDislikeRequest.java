package com.aurd.Student.Model.Request;

import java.sql.Timestamp;

public class BlogLikeDislikeRequest {

    int operation;
    int blog_id;
    int added_by;
    Timestamp added_on;

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
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
}
