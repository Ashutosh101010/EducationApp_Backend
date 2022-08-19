package com.aurd.Student.Model.Request;

public class GetAudioNotesRequest {
    int instID;
    int topicID;
    String filter;

    public void setInstID(int instID) {
        this.instID = instID;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public long getInstID() {
        return instID;
    }





    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
