package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.Book;
import com.aurd.Student.Model.Request.GetPhysicalBookRequest;
import com.aurd.Student.Model.Response.GetPhysicalBookResponse;
import com.aurd.Student.Repository.BookRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/getPhysicalBook")
public class GetPhysicalBook {


    @Inject
    BookRepository bookRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetPhysicalBookResponse response(GetPhysicalBookRequest request)
    {
        GetPhysicalBookResponse response=new GetPhysicalBookResponse();

        List<Book> list=bookRepository.find("inst_id",request.getInst_id()).list();
        response.setBooks(list);
        response.setMessage("Get Physical Book");
        response.setStatus(true);
        response.setErrorCode(0);

        return response;
    }
}
