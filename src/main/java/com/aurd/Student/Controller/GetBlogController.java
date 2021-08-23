package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Request.GetBlogRequest;
import com.aurd.Student.Model.Response.GetBlogResponse;
import com.aurd.Student.Repository.BlogRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getBlogs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetBlogController {

    @Inject
    BlogRepository repository;
    @Transactional
    @POST


    public GetBlogResponse getBlogs(GetBlogRequest request){

      ArrayList<BlogModel> arrayList = repository.getBlogs(request);
      GetBlogResponse getBlogResponse= new GetBlogResponse();
      getBlogResponse.setBlogs(arrayList);
      getBlogResponse.setMessage("Get Blogs Successfully");
      getBlogResponse.setStatus(0);
      getBlogResponse.setStatusCode(true);


      return getBlogResponse;

    }



}
