package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.NoticeModel;
import com.aurd.Student.Model.Request.GetNoticeRequest;
import com.aurd.Student.Model.Response.GetNoticeResponse;
import com.aurd.Student.Repository.GetNoticeRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getNotice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetNoticeController {

    @Inject
    GetNoticeRepository repository;
    @Transactional
    @POST


    public GetNoticeResponse getNotice(GetNoticeRequest request){

        ArrayList<NoticeModel> arrayList = repository.getNotice(request);
        System.out.println(arrayList.size());
        GetNoticeResponse getNoticeResponse= new GetNoticeResponse();
        getNoticeResponse.setNotice(arrayList);
        getNoticeResponse.setMessage("Get Notice Successfully");
        getNoticeResponse.setStatus(true);
        getNoticeResponse.seterrorCode(0);


        return getNoticeResponse;

    }



}
