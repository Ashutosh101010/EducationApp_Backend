package com.aurd.Student.Model.BeanClass;

import java.sql.Timestamp;

public class StudentPostEntity {
    String description;
    Timestamp added_on;
    Long id;
    String pic;
    int postStatus;
    Integer added_by;
    String name;
    Long comment;
    Long like;
    boolean isLiked;
    boolean disLiked;
    boolean added;
    String type;
    private long indexId;
    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getIndexId() {
        return indexId;
    }

    public void setIndexId(long indexId) {
        this.indexId = indexId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isDisLiked() {
        return disLiked;
    }

    public void setDisLiked(boolean disLiked) {
        this.disLiked = disLiked;
    }

    public Long getComment() {
        return comment;
    }

    public void setComment(Long comment) {
        this.comment = comment;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(int postStatus) {
        this.postStatus = postStatus;
    }

    public Integer getAdded_by() {
        return added_by;
    }

    public void setAdded_by(Integer added_by) {
        this.added_by = added_by;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
