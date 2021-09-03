package com.aurd.Student.Model.Request;

public class GetVideoLectureRequest {
    long instID;
    long topicID;

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
