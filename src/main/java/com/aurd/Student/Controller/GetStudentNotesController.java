package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.StudentNotesModel;
import com.aurd.Student.Model.Request.GetStudentNotesRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Model.Response.StudentNotesResponse;
import com.aurd.Student.Repository.StudentNotesRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getStudentNotes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetStudentNotesController {

    @Inject
    StudentNotesRepository repository;
    @POST
    @Transactional

    public StudentNotesResponse getStudNotes(GetStudentNotesRequest request){

        ArrayList<StudentNotesModel> arrayList =repository.getStudentNotes(request);
       StudentNotesResponse response = new StudentNotesResponse();
       response.setNotes(arrayList);
       response.setMessage("Get student notes successfully");
       response.setStatus(true);
       response.setStatusCode(0);

        return  response;


    }



}
