package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.AuthCheckRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/authCheck")
public class AuthCheckController {

    @Inject
    StudentRepository studentRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GeneralResponse authCheck(AuthCheckRequest request)
    {


        GeneralResponse response=new GeneralResponse();


        Query query=studentRepository.getEntityManager().createQuery("select StudentModel  from StudentModel StudentModel where StudentModel.deviceId=:id");
        query.setParameter("id",request.getDeviceId());

        List<StudentModel> list=query.getResultList();

        if(list.isEmpty())
        {
            response.seterrorCode(111);
            response.setStatus(false);
            response.setMessage("not verified");

            return response;
        }

        else{

            if(list.get(0).getDeviceId().trim().equals(request.getDeviceId().trim()))

            {


                response.seterrorCode(0);
                response.setStatus(true);
                response.setMessage("verified");

                return response;
            }
            else{
                response.seterrorCode(111);
                response.setStatus(false);
                response.setMessage(" not verified");

                return response;
            }
        }



    }
}
