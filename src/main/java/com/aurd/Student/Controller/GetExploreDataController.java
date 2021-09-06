package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Response.GetExploreDataResponse;
import com.aurd.Student.Model.Response.LatestUpdateResponse;
import com.aurd.Student.Repository.*;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("/getExploreData")
public class GetExploreDataController {

    @Inject
    BlogRepository blogRepository;

    @Inject
    CurrentAffairRepository currentAffairRepository;

    @Inject
    StudentPostRepository postRepository;

    @Inject
    NotesRepository notesRepository;

    @Inject
    QuizRepository quizRepository;

    @Inject
    StudentPostCommentRepository commentRepository;

    @Inject
    StudentPostLikedRepository likedRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)



    @Transactional

    public GetExploreDataResponse getData(@QueryParam("instID") Long id){


        String blogQuery = "SELECT * from `blog` where inst_id = ? ;";
        Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
        blog.setParameter(1,id);
        ArrayList<BlogModel> blogList = (ArrayList<BlogModel>) blog.getResultList();

        String caQuery = "SELECT * from `current_affairs` where  inst_id = ? ;";
        Query currentAffair = currentAffairRepository.getEntityManager().createNativeQuery(caQuery,
                CurrentAffairModel.class);
        currentAffair.setParameter(1,id);
        ArrayList<CurrentAffairModel> caList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();


        String studentPostQuery = "SELECT student_posts.id,student_posts.discription,student_posts.pic,student_posts.post_status,student_posts.added_by,\n" +
                "student_posts.added_on, students.fname FROM `student_posts` INNER JOIN students ON students.id=student_posts.added_by " +
                "WHERE student_posts.inst_id = ?";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
       studentPost.setParameter(1,id);

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
            comment.setParameter(1,12);
            Integer commentCount = ((Number) comment.getSingleResult()).intValue();
            postModel.setComment(commentCount.longValue());


            String likeQuery = "SELECT COUNT(*) FROM `student_posts_liked` WHERE post_id =? ";
            Query like = likedRepository.getEntityManager().createNativeQuery(likeQuery);
            like.setParameter(1,13);
            Integer likeCount = ((Number) like.getSingleResult()).intValue();
            postModel.setLike(likeCount.longValue());

            postList.add(postModel);
        });

        ArrayList<NotesModel> notesList = (ArrayList<NotesModel>) notesRepository.find("inst_id",id.intValue()).list();

        GetQuizRequest request = new GetQuizRequest();
        request.setInst_id(id);
        request.setType("Quiz");
        ArrayList<QuizModel> quizList = quizRepository.getQuizzes(request);

        GetExploreDataResponse response = new GetExploreDataResponse();
        response.setBlogList(blogList);
        response.setNotesList(notesList);
        response.setPostList(postList);
        response.setCurrentAffairArrayList(caList);
        response.setQuizList(quizList);
        response.setErrorCode(0);
        response.setStatus(true);
        response.setMessage("Fetch Explore Data Success");


       return response;
    }
}
