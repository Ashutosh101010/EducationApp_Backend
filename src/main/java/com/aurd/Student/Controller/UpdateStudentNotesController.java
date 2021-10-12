package com.aurd.Student.Controller;

import com.aurd.Student.Model.Request.UpdateStudentNotesRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentNotesRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/students/updateStudentNotes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UpdateStudentNotesController {



    @Inject
    StudentNotesRepository repository;
    @POST
    @Transactional

    public GeneralResponse updateNotes(UpdateStudentNotesRequest request){

      String string = "UPDATE student_notes SET note=?, title = ? WHERE student_notes.id = ?";
        Query query = repository.getEntityManager().createNativeQuery(string);
        query.setParameter(1,request.getNote());
        query.setParameter(2,request.getTitle());
        query.setParameter(3,request.getId());

        query.executeUpdate();

        GeneralResponse response = new GeneralResponse();
        response.setStatus(true);
        response.setMessage("Update notes successfull");
        response.setStatusCode(0);

        return  response;

    }

}
