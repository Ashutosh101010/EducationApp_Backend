package com.aurd.Student.Controller;

import com.aurd.Student.Model.Request.AddNotesRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentNotesRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/students/addStudentNotes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddNotesController {


    @Inject
    StudentNotesRepository repository;

    @POST

    @Transactional
    public GeneralResponse addStudentNotes(AddNotesRequest request){
        GeneralResponse response = new GeneralResponse();

        boolean value= repository.addStudentNotes(request);
        if(value){
            response.seterrorCode(0);
            response.setStatus(true);
            response.setMessage("Notes Added");
        }else{
            response.seterrorCode(1);
            response.setStatus(false);
            response.setMessage("Notes Not Added");
        }

        return  response;


    }




}
