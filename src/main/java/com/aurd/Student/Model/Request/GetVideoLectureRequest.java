package com.aurd.Student.Model.Request;

public class GetVideoLectureRequest {
    Integer instID;
    Integer topicID;
    String filter;


    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getTopicID() {
        return topicID;
    }

    public void setTopicID(Integer topicID) {
        this.topicID = topicID;
    }

    public Integer getInstID() {
        return instID;
    }

    public void setInstID(Integer instID) {
        this.instID = instID;
    }
}
