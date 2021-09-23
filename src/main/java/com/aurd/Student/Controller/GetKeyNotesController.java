package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Entity.KeyNotesModel;
import com.aurd.Student.Model.Request.AddKeyNotes;

import com.aurd.Student.Model.Request.GetKeyNotesRequest;



import com.aurd.Student.Model.Response.GetKeyNotesResponse;
import com.aurd.Student.Repository.GetKeyNotesRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;



@Path("/getKeyNotes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class GetKeyNotesController {

    @Inject
GetKeyNotesRepository repository;


    @POST

    @Transactional

    public GetKeyNotesResponse getList(GetKeyNotesRequest request){





        ArrayList<KeyNotesModel> list = repository.getKeyNotesList(request);

        GetKeyNotesResponse getKeyNotesResponse = new GetKeyNotesResponse();




        if(list.isEmpty()){

            getKeyNotesResponse.setMessage("No KeyNotes");
            getKeyNotesResponse.setStatus(false);
            getKeyNotesResponse.setStatusCode(1);

        }else{
            getKeyNotesResponse.setKeynotes(list);
            getKeyNotesResponse.setMessage("Get KeyNotes Success");
            getKeyNotesResponse.setStatus(true);
            getKeyNotesResponse.setStatusCode(0);
        }


        return  getKeyNotesResponse;

    }
}
