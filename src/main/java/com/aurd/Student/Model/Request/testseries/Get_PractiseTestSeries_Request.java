package com.aurd.Student.Model.Request.testseries;

public class Get_PractiseTestSeries_Request {
    long quiz_id;
    long inst_id;

    public long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }
}
