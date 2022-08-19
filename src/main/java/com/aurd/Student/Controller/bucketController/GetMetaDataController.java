//package com.aurd.Student.Controller.bucketController;
//
//import com.aurd.Student.Model.Entity.StateModel;
//import com.aurd.Student.Model.Response.GetMetaDataResponse;
//import com.aurd.Student.Repository.StateRepository;
//
//import javax.inject.Inject;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/getMetaData")
//public class GetMetaDataController {
//
//    @Inject
//    StateRepository repository;
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public GetMetaDataResponse getMetaDataResponse()
//    {
//        List<StateModel> states=repository.getState();
//        GetMetaDataResponse response=new GetMetaDataResponse();
//        response.setStates(states);
//        return response;
//    }
//}
