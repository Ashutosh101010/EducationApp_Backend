package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.CityModel;
import com.aurd.Student.Model.Request.GetCityRequest;
import com.aurd.Student.Model.Response.GetCityResponse;
import com.aurd.Student.Repository.CityRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;



    @Path("/getCity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class GetCityController {


        @Inject
        CityRepository repository;
        @Transactional
        @POST


        public GetCityResponse getCity(GetCityRequest request) {

            ArrayList<CityModel> arrayList = (ArrayList<CityModel>) repository.getCity(request.getState_id());
            GetCityResponse getCityResponse = new GetCityResponse();
            getCityResponse.setCities(arrayList);
            getCityResponse.setMessage("Get City Successfully");
            getCityResponse.setStatus(true);
            getCityResponse.setStatusCode(0);

            return getCityResponse;
        }



}
