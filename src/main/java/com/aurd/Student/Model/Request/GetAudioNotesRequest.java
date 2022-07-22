package com.aurd.Student.Model.Request;

public class GetAudioNotesRequest {
    long instID;
    long topicID;
    String filter;

    public long getInstID() {
        return instID;
    }

    public void setInstID(long instID) {
        this.instID = instID;
    }

    public long getTopicID() {
        return topicID;
    }

    public void setTopicID(long topicID) {
        this.topicID = topicID;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
