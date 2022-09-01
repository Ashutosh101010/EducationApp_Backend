package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.BooksMedia;
import com.aurd.Student.Model.Request.GetPhysicalBookMediaRequest;
import com.aurd.Student.Model.Response.GetPhysicalBookMediaResponse;
import com.aurd.Student.Repository.BookMediaRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/getPhysicalBookMedia")
public class GetPhysicalBookMedia {


    @Inject
    BookMediaRepository repository;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetPhysicalBookMediaResponse response(GetPhysicalBookMediaRequest request)
    {
        GetPhysicalBookMediaResponse response=new GetPhysicalBookMediaResponse();
        List<BooksMedia> list=repository.find("book_id",request.getBookId()).list();
        response.setErrorCode(0);
        response.setMessage("Get Physical Book Media");
        response.setStatus(true);
        response.setMediaList(list);
        return response;
    }
}
