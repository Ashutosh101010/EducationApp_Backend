package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.CommentEntity;
import com.aurd.Student.Model.Entity.Current_AffairsCommented_Model;
import com.aurd.Student.Model.Entity.Student_Blog_Commented_Model;
import com.aurd.Student.Model.Request.GetCommentRequest;
import com.aurd.Student.Model.Response.StudentPostCommentResponse;
import com.aurd.Student.Repository.StudentPostCommentRepository;
import com.aurd.Student.Repository.comment.Blog_Comment_Repository;
import com.aurd.Student.Repository.comment.Current_Affair_Comment_Repository;

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

@Path("/getAllComment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class GetAllCommentController {

    @Inject
    StudentPostCommentRepository repository;

    @Inject
    Blog_Comment_Repository blog_repository;

    @Inject
    Current_Affair_Comment_Repository ca_repository;


    @Transactional
    @POST


    public StudentPostCommentResponse getComment(GetCommentRequest request) {
        ArrayList<CommentEntity> arrayList = new ArrayList();


        StudentPostCommentResponse response = new StudentPostCommentResponse();

        if(request.getType().equals("studentPost")){
            String string ="SELECT student_posts_commented.comment ,student_posts_commented.comment_id,\n" +
                    "                student_posts_commented.post_id, student_posts_commented.added_on,students.fname \n" +
                    "        FROM student_posts_commented INNER JOIN students ON students.id = student_posts_commented.added_by \n" +
                    "        WHERE student_posts_commented.post_id =?";

            Query query = repository.getEntityManager().createNativeQuery(string);
            query.setParameter(1,request.getPost_id());

            ArrayList<Object[]> list = (ArrayList<Object[]>) query.getResultList();
            if(list.isEmpty()){
                response.setStatusCode(1);
                response.setStatus(false);
                response.setMessage("No comment Found");
            }else{
                list.forEach(objects -> {
                    CommentEntity commentEntity = new CommentEntity();
                    commentEntity.setComment(objects[0].toString());
                    commentEntity.setComment_id(Integer.parseInt(objects[1].toString()));
                    commentEntity.setPost_id(Integer.parseInt(objects[2].toString()));
                    commentEntity.setAdded_on(Timestamp.valueOf(objects[3].toString()));
                    commentEntity.setFname(objects[4].toString());

                    arrayList.add(commentEntity);

                });

                response.setComments(arrayList);
                response.setStatusCode(0);
                response.setStatus(true);
                response.setMessage("Get comment Success");
            }


        }else if (request.getType().equals("blog")) {

            String string = "SELECT blog_commented.comment ,blog_commented.comment_id,\n" +
                    "                blog_commented.blog_id, blog_commented.added_on,students.fname \n" +
                    "        FROM blog_commented INNER JOIN students ON students.id = blog_commented.added_by \n" +
                    "        WHERE blog_commented.blog_id =?";

//            ArrayList<Student_Blog_Commented_Model> blogCommentList = blog_repository.getComment(request);
            Query query = blog_repository.getEntityManager().createNativeQuery(string);
            query.setParameter(1,request.getPost_id());
            ArrayList<Object[]> blogCommentList = (ArrayList<Object[]>) query.getResultList();
            System.out.println(arrayList.size());

            if (blogCommentList.isEmpty()) {
                response.setStatusCode(1);
                response.setStatus(false);
                response.setMessage("No comment Found");
            } else {

                blogCommentList.forEach(objects -> {
                    CommentEntity commentEntity = new CommentEntity();
                    commentEntity.setComment(objects[0].toString());
                    commentEntity.setComment_id(Integer.parseInt(objects[1].toString()));
                    commentEntity.setPost_id(Integer.parseInt(objects[2].toString()));
                    commentEntity.setAdded_on(Timestamp.valueOf(objects[3].toString()));
                    commentEntity.setFname(objects[4].toString());

                    arrayList.add(commentEntity);
                });

                response.setComments(arrayList);
                response.setStatusCode(0);
                response.setStatus(true);
                response.setMessage("Get comment Success");
            }

        } else if (request.getType().equals("currentAffair")) {

            String string = "SELECT current_affairs_comments.comment ,current_affairs_comments.comment_id,\n" +
                    "                current_affairs_comments.current_affair_id," +
                    " current_affairs_comments.added_on, students.fname \n" +
                    "        FROM current_affairs_comments INNER JOIN students ON students.id =" +
                    " current_affairs_comments.added_by \n" +
                    "        WHERE current_affairs_comments.current_affair_id = ?";

            Query query = ca_repository.getEntityManager().createNativeQuery(string);
            query.setParameter(1,request.getPost_id());
            ArrayList<Object[]> caList = (ArrayList<Object[]>) query.getResultList();

            if (caList.isEmpty()) {
                response.setStatusCode(1);
                response.setStatus(false);
                response.setMessage("No comment Found");
            } else {

                caList.forEach(objects -> {
                    CommentEntity commentEntity = new CommentEntity();
                    commentEntity.setComment(objects[0].toString());
                    commentEntity.setComment_id(Integer.parseInt(objects[1].toString()));
                    commentEntity.setPost_id(Integer.parseInt(objects[2].toString()));
                    commentEntity.setAdded_on(Timestamp.valueOf(objects[3].toString()));
                    commentEntity.setFname(objects[4].toString());

                    arrayList.add(commentEntity);
                });

                response.setComments(arrayList);
                response.setStatusCode(0);
                response.setStatus(true);
                response.setMessage("Get comment Success");
            }

        }



        return response;
    }
}
