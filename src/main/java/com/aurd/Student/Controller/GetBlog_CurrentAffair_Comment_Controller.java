package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.Current_AffairsCommented_Model;
import com.aurd.Student.Model.Entity.Student_Blog_Commented_Model;
import com.aurd.Student.Model.Request.GetBlogAndCurrentAffairCommentRequest;

import com.aurd.Student.Model.Response.GetBlogAndCurrentAffairCommentResponse;
import com.aurd.Student.Repository.comment.Blog_Comment_Repository;
import com.aurd.Student.Repository.comment.Current_Affair_Comment_Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getComments")

public class GetBlog_CurrentAffair_Comment_Controller {

    @Inject
    Blog_Comment_Repository blog_repository;

    @Inject
    Current_Affair_Comment_Repository ca_repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public GetBlogAndCurrentAffairCommentResponse getComments(GetBlogAndCurrentAffairCommentRequest request) {



        GetBlogAndCurrentAffairCommentResponse response = new GetBlogAndCurrentAffairCommentResponse();

        if (request.getType().equals("blog")) {

            ArrayList<Student_Blog_Commented_Model> arrayList = blog_repository.getComment(request);
            System.out.println(arrayList.size());

            if (arrayList.isEmpty()) {
                response.setStatusCode(1);
                response.setStatus(false);
                response.setMessage("No comment Found");
            } else {
                response.setBlogCommentList(arrayList);
                response.setStatusCode(0);
                response.setStatus(true);
                response.setMessage("Get comment Success");
            }


        } else if (request.getType().equals("currentAffair")) {
            ArrayList<Current_AffairsCommented_Model> arrayList1 = ca_repository.getComment(request);
            System.out.println(arrayList1.size());

            if (arrayList1.isEmpty()) {
                response.setStatusCode(1);
                response.setStatus(false);
                response.setMessage("No comment Found");
            } else {
                response.setCaList(arrayList1);
                response.setStatusCode(0);
                response.setStatus(true);
                response.setMessage("Get comment Success");
            }
        }
        return response;
    }
}
