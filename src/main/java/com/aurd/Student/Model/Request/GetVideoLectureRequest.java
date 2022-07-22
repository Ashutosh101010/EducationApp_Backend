package com.aurd.Student.Model.Request;

public class GetVideoLectureRequest {
    long instID;
    long topicID;
    String filter;


    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public long getTopicID() {
        return topicID;
    }

    public void setTopicID(long topicID) {
        this.topicID = topicID;
    }

    public long getInstID() {
        return instID;
    }

    public void setInstID(long instID) {
        this.instID = instID;
    }
}
