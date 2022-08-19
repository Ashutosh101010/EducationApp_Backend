package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.CityModel;
import com.aurd.Student.Model.Entity.StateModel;
import com.aurd.Student.Model.Response.MetaDataResponse;
import com.aurd.Student.Repository.CityRepository;
import com.aurd.Student.Repository.StateRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


    @Path("/getMetaData")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class GetMetaDataController {


        @Inject
        StateRepository stateRepository;

        @Inject
        CityRepository cityRepository;

        @Transactional
        @GET
        public MetaDataResponse getState() {

            ArrayList<StateModel>  states= getStates();
            ArrayList<CityModel> cities = getCities();




            MetaDataResponse getStateResponse = new MetaDataResponse();
            getStateResponse.setStateList(states);
            getStateResponse.setCityList(cities);
            getStateResponse.setMessage("Get State Successfully");
            getStateResponse.setStatus(true);
            getStateResponse.setErrorCode(0);

            return getStateResponse;
        }

        public ArrayList getStates(){
            ArrayList<StateModel> arrayList = (ArrayList<StateModel>) stateRepository.getState();
//            ArrayList<String> states=new ArrayList<>();
//
//            arrayList.forEach(stateModel -> {
//                states.add(stateModel.getName());
//            });

            return  arrayList;
        }

        public ArrayList getCities(){

            ArrayList<CityModel> arrayList = (ArrayList<CityModel>) cityRepository.listAll();
            return  arrayList;
        }

}
