package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.StateModel;
import com.aurd.Student.Model.Response.GetStateResponse;
import com.aurd.Student.Repository.StateRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


    @Path("/getState")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class GetStateController {


        @Inject
        StateRepository repository;
        @Transactional
        @GET


        public GetStateResponse getState() {

            ArrayList<StateModel> arrayList = (ArrayList<StateModel>) repository.getState();
            ArrayList<String> states=new ArrayList<>();
            arrayList.forEach(stateModel -> {states.add(stateModel.getName());

            });
            arrayList.forEach(stateModel -> {
                states.add(stateModel.getName());
            });
            GetStateResponse getStateResponse = new GetStateResponse();
            getStateResponse.setStates(states);
            getStateResponse.setMessage("Get State Successfully");
            getStateResponse.setStatus(true);
            getStateResponse.setStatusCode(0);

            return getStateResponse;
        }

}
