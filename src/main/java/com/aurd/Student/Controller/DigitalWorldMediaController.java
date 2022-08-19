//package com.aurd.Student.Controller;
//
//import com.aurd.Student.Model.Entity.DigitalWorlMedia;
//import com.aurd.Student.Model.Request.DigitalWorldMediaRequest;
//import com.aurd.Student.Model.Response.DigitalWorldMediaResponse;
//
//import com.aurd.Student.Repository.DigitalWorldMediaRepository;
//
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/digitalWorldMedia")
//public class DigitalWorldMediaController {
//
//    @Inject
//    DigitalWorldMediaRepository repository;
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public DigitalWorldMediaResponse mediaResponse(DigitalWorldMediaRequest request)
//    {
//        DigitalWorldMediaResponse response=new DigitalWorldMediaResponse();
//
//        if(request.getFolderId()==null || request.getPage()==null || request.getPageSize()==null)
//        {
//            response.setMessage("Invalid Request");
//            response.setStatus(false);
//            response.setErrorCode(1);
//            return response;
//        }
//
//        List<DigitalWorlMedia> list=repository.find("folder_id",request.getFolderId()).page(request.getPage(),request.getPageSize()).list();
//
//        response.setErrorCode(0);
//        response.setStatus(true);
//        response.setMediaList(list);
//        return response;
//    }
//}
