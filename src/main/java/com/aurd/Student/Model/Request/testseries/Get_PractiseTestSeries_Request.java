package com.aurd.Student.Model.Request.testseries;

public class Get_PractiseTestSeries_Request {
    int topic_id;
    int inst_id;

    long course_id;



    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }
}
