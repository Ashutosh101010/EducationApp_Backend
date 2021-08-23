package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Request.GetStudentPostRequest;
import com.aurd.Student.Model.Response.GetStudentPostResponse;
import com.aurd.Student.Repository.StudentPostRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getStudentPost")
public class GetStudentPostsController {

    @Inject
    StudentPostRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional

    public GetStudentPostResponse getPosts(GetStudentPostRequest request){

        ArrayList<StudentPostModel> arrayList= repository.getStudentPosts(request);
        GetStudentPostResponse response = new GetStudentPostResponse();
        if(arrayList.isEmpty()){
            response.setErrorCode(1);
            response.setStatus(false);
            response.setMessage("No Post Found");
        }else {
            response.setPosts(arrayList);
            response.setErrorCode(0);
            response.setStatus(true);
            response.setMessage("Get Post Success");
        }


        return  response;
    }


}
