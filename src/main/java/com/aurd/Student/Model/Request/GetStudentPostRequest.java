package com.aurd.Student.Model.Request;

public class GetStudentPostRequest {
    Long stud_id;
    int inst_id;
    String lastId;

    public Long getStud_id() {
        return stud_id;
    }

    public void setStud_id(Long stud_id) {
        this.stud_id = stud_id;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }
}
