package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Request.GetStudentPostRequest;
import com.aurd.Student.Model.Response.GetStudentPostResponse;
import com.aurd.Student.Repository.StudentPostCommentRepository;
import com.aurd.Student.Repository.StudentPostLikedRepository;
import com.aurd.Student.Repository.StudentPostRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;

@Path("/getStudentActivity")
public class GetStudentPostsController {

    @Inject
    StudentPostRepository postRepository;
    @Inject
    StudentPostCommentRepository commentRepository;

    @Inject
    StudentPostLikedRepository likedRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional

    public GetStudentPostResponse getPosts(GetStudentPostRequest request){

//        ArrayList<StudentPostModel> arrayList= repository.getStudentPosts(request);
        GetStudentPostResponse response = new GetStudentPostResponse();
//        if(arrayList.isEmpty()){
//            response.setErrorCode(1);
//            response.setStatus(false);
//            response.setMessage("No Post Found");
//        }else {
//            response.setPosts(arrayList);
//            response.setErrorCode(0);
//            response.setStatus(true);
//            response.setMessage("Get Post Success");
//        }


        String studentPostQuery = "SELECT student_posts.id,student_posts.description," +
                "student_posts.pic,student_posts.post_status,student_posts.added_by,\n" +
                "student_posts.added_on, students.fname FROM `student_posts` " +
                "INNER JOIN students ON students.id=student_posts.added_by " +
                "WHERE student_posts.inst_id = ? and student_posts.added_by = ?";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1,request.getInst_id());
        studentPost.setParameter(2,request.getStud_id());

        ArrayList<Object[]> tempPostList = (ArrayList<Object[]>) studentPost.getResultList();
        ArrayList<StudentPostEntity> postList = new ArrayList<>();
        tempPostList.forEach(objects -> {
            StudentPostEntity postModel = new StudentPostEntity();
            postModel.setId(Long.parseLong(objects[0].toString()));
            postModel.setDescription(objects[1].toString());
            postModel.setPic(objects[2].toString());
            postModel.setPostStatus(Integer.parseInt(objects[3].toString()));
            postModel.setAdded_by(Integer.parseInt(objects[4].toString()));
            postModel.setAdded_on(Timestamp.valueOf(objects[5].toString()));
            postModel.setName(objects[6].toString());

            String commentQuery = "SELECT COUNT(*) FROM `student_posts_commented` WHERE post_id =? ";
            Query comment = commentRepository.getEntityManager().createNativeQuery(commentQuery);
            comment.setParameter(1,postModel.getId());
            Integer commentCount = ((Number) comment.getSingleResult()).intValue();
            postModel.setComment(commentCount.longValue());


            String likeQuery = "SELECT * FROM `student_posts_liked` WHERE post_id =?";
            Query like = likedRepository.getEntityManager().createNativeQuery(likeQuery);
            like.setParameter(1,postModel.getId());
            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
            likeList.forEach(likeObject -> {
                if(request.getStud_id() == Long.parseLong(likeObject[1].toString())){
                    System.out.println("Liked");
                    postModel.setLiked(true);
                }
            });

            Integer likeCount =  likeList.size();
            postModel.setLike(likeCount.longValue());

            postList.add(postModel);
        });


        if(postList.isEmpty()){
            response.setMessage("No Post Found");
            response.setStatus(false);
            response.setErrorCode(1);

        }else{
            response.setPosts(postList);
            response.setMessage("Post found success");
            response.setErrorCode(0);
            response.setStatus(true);
        }


        return  response;
    }


}
