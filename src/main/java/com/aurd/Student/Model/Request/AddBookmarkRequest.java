package com.aurd.Student.Model.Request;

public class AddBookmarkRequest {
    int post_id;
    String type;
    int added_by;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }
}
