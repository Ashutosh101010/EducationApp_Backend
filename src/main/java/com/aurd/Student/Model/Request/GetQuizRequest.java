package com.aurd.Student.Model.Request;

public class GetQuizRequest {
    long inst_id;
    String type;

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
