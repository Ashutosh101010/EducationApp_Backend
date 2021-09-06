package com.aurd.Student.Controller;


import com.aurd.Student.Model.Request.AddKeyNotes;
import com.aurd.Student.Model.Request.AddNotesRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.AddKeyNotesRepository;
import com.aurd.Student.Repository.StudentNotesRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/addKeynotes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class AddKeyNotesController {
    @Inject

    AddKeyNotesRepository repository;

    @POST

    @Transactional
    public GeneralResponse addKeyNotes(AddKeyNotes request) {
        GeneralResponse response = new GeneralResponse();

        boolean value = repository.addKeyNotesRequest(request);
        if (value) {
            response.setStatusCode(0);
            response.setStatus(true);
            response.setMessage("Notes Added");
        } else {
            response.setStatusCode(1);
            response.setStatus(false);
            response.setMessage("Notes Not Added");
        }

        return response;

    }
}