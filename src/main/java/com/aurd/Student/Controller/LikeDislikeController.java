package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.Student_Posts_Liked_Model;
import com.aurd.Student.Model.Request.LikeDislikeRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentPostLikedRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static io.quarkus.hibernate.orm.panache.Panache.getEntityManager;

@Path("/likeDislike")
public class LikeDislikeController {
    @Inject
    StudentPostLikedRepository likedRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public GeneralResponse likeDislikePost(LikeDislikeRequest request){
        GeneralResponse response = new GeneralResponse();

        Student_Posts_Liked_Model model = new Student_Posts_Liked_Model();

        if(request.getOperation()==1){
            model.setPost_id(request.getPostId());
            model.setAdded_by(request.getStud_id());
            likedRepository.persist(model);

            response.setMessage("Post Liked");
            response.setStatus(true);
            response.setStatusCode(0);

        }else if(request.getOperation()==2){
            String dislike = "DELETE FROM student_posts_liked WHERE id=?";

            Query query = getEntityManager().createNativeQuery(dislike);
            query.setParameter(1,request.getId());

            query.executeUpdate();


            response.setMessage("Post Dislike");
            response.setStatus(true);
            response.setStatusCode(0);
        }else{
            response.setMessage("Unable to proceed");
            response.setStatus(false);
            response.setStatusCode(1);
        }

        return  response;

    }

}
