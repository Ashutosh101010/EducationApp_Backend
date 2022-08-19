package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.DistrictModal;
import com.aurd.Student.Model.Entity.StateModel;

import java.util.List;

public class GetMetaDataResponse {


    List<StateModel> states;

    public List<StateModel> getStates() {
        return states;
    }

    public void setStates(List<StateModel> states) {
        this.states = states;
    }
}
