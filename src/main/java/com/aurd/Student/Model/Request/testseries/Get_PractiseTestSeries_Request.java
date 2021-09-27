package com.aurd.Student.Model.Request.testseries;

public class Get_PractiseTestSeries_Request {
    int topic_id;
    int inst_id;
    long course_id;

    int test_series_id;

    public int getTest_series_id() {
        return test_series_id;
    }

    public void setTest_series_id(int test_series_id) {
        this.test_series_id = test_series_id;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }


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
