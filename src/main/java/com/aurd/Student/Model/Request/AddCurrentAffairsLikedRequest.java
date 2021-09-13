package com.aurd.Student.Model.Request;

import java.sql.Timestamp;

public class AddCurrentAffairsLikedRequest {


    int current_affair_id;
    int added_by;
    Timestamp added_on;

    public int getCurrent_affair_id() {
        return current_affair_id;
    }

    public void setCurrent_affair_id(int current_affair_id) {
        this.current_affair_id = current_affair_id;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }
}
