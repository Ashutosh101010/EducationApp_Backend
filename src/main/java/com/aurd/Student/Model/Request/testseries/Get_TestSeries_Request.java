
package com.aurd.Student.Model.Request.testseries;

public class Get_TestSeries_Request {

    int inst_id;
     String type;
     long stud_id;

    public long getStud_id() {
        return stud_id;
    }

    public void setStud_id(long stud_id) {
        this.stud_id = stud_id;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
