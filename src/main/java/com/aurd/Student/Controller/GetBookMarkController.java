package com.aurd.Student.Controller;



import com.aurd.Student.Model.Entity.BookMarkModel;
import com.aurd.Student.Model.Request.GetBookMarkRequest;
import com.aurd.Student.Model.Response.GetBookMarkResponse;
import com.aurd.Student.Repository.BookMarkRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;



@Path("/getBookMark")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetBookMarkController {


    @Inject
    BookMarkRepository repository;
    @Transactional
    @POST


    public GetBookMarkResponse getBook(GetBookMarkRequest request) {

        ArrayList<BookMarkModel> arrayList = repository.getBooks(request);
        GetBookMarkResponse getBookResponse = new GetBookMarkResponse();
        getBookResponse.setBook(arrayList);
        getBookResponse.setMessage("Get BookMark Successfully");
        getBookResponse.setStatus(true);
        getBookResponse.setStatusCode(0);

        return getBookResponse;
    }



}
