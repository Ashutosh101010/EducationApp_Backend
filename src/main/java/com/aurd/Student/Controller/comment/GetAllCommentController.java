package com.aurd.Student.Controller.comment;

import com.aurd.Student.Model.BeanClass.CommentEntity;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetCommentRequest;
import com.aurd.Student.Model.Response.StudentPostCommentResponse;
import com.aurd.Student.Repository.CommentReplyRepository;
import com.aurd.Student.Repository.NotesCommentRepository;
import com.aurd.Student.Repository.StudentPostCommentRepository;
import com.aurd.Student.Repository.StudentRepository;
import com.aurd.Student.Repository.comment.Blog_Comment_Repository;
import com.aurd.Student.Repository.comment.Current_Affair_Comment_Repository;
import com.google.gson.Gson;

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


    @Inject
    CommentReplyRepository replyRepository;

    @Inject
    NotesCommentRepository notesComentRepository;

    @Inject
    StudentRepository studentRepository;

    @Transactional
    @POST


    public StudentPostCommentResponse getComment(GetCommentRequest request) {
        StudentPostCommentResponse response = new StudentPostCommentResponse();

        ArrayList<CommentEntity> arrayList = new ArrayList<>();

        if(request.getType().equals("blog")){

          ArrayList<Blog_Comment_Model> commentList = (ArrayList<Blog_Comment_Model>)
                  blog_repository.list("blog_id",request.getPost_id());
          commentList.forEach(model -> {
              CommentEntity entity = new CommentEntity();
              entity.setComment(model.getComment());
              entity.setComment_id(model.getComment_id());
            //  entity.setFname(model.getStud_name());

              StudentModel studentModel = studentRepository.find("id",Long.valueOf(model.getAdded_by())).firstResult();
              entity.setFname(studentModel.getFname());

              entity.setPost_id(model.getBlog_id_id());
              entity.setUser_id(model.getAdded_by());
              entity.setAdded_on(model.getAdded_on());
              entity.setTime(model.getAdded_on().toString());

              ArrayList<Comment_Reply_Model> rList =  getCommentReply(entity.getComment_id());
              entity.setReplyList(rList);

              arrayList.add(entity);

          });

          if(arrayList.isEmpty()){

              response.setComments(arrayList);
              response.setMessage("Comment not found");
              response.setStatus(false);
              response.setStatusCode(1);
          }else{
              response.setComments(arrayList);
              response.setMessage("Get comment Successful");
              response.setStatus(true);
              response.setStatusCode(0);
          }

        }
        else if(request.getType().equals("currentAffair")){

            ArrayList<Current_AffairsCommented_Model> commentList =
                    (ArrayList<Current_AffairsCommented_Model>)
                    ca_repository.list("current_affair_id",request.getPost_id());
            commentList.forEach(model -> {
                CommentEntity entity = new CommentEntity();
                entity.setComment(model.getComment());
                entity.setComment_id(model.getComment_id());
             //   entity.setFname(model.getStud_name());

                StudentModel studentModel = studentRepository.find("id",Long.valueOf(model.getAdded_by())).firstResult();
                entity.setFname(studentModel.getFname());

                entity.setPost_id(model.getCurrent_affair_id());
                entity.setUser_id(model.getAdded_by());
                entity.setAdded_on(model.getAdded_on());
                entity.setTime(model.getAdded_on().toString());

                ArrayList<Comment_Reply_Model> rList =  getCommentReply(entity.getComment_id());
                entity.setReplyList(rList);

                arrayList.add(entity);

            });

            if(arrayList.isEmpty()){

                response.setComments(arrayList);
                response.setStatus(false);
                response.setStatusCode(1);
            }else{
                response.setComments(arrayList);
                response.setStatus(true);
                response.setStatusCode(0);
            }
        }
        else  if(request.getType().equals("studentPost")){

            ArrayList<Student_Posts_Commented> commentList = (ArrayList<Student_Posts_Commented>)
                    repository.list("post_id",request.getPost_id());
            commentList.forEach(model -> {
                CommentEntity entity = new CommentEntity();
                entity.setComment(model.getComment());
                entity.setComment_id(model.getComment_id());

                entity.setPost_id(model.getPost_id());

                StudentModel studentModel = studentRepository.find("id",model.getAdded_by()).firstResult();

                entity.setFname(studentModel.getFname());

                System.out.println(studentModel.getFname());

               Integer integer = Math.toIntExact(model.getAdded_by());
                entity.setUser_id(integer);
                entity.setAdded_on(model.getAdded_on());
                entity.setTime(model.getAdded_on().toString());

                ArrayList<Comment_Reply_Model> rList =  getCommentReply(entity.getComment_id());
                entity.setReplyList(rList);

                arrayList.add(entity);

            });

            if(arrayList.isEmpty()){

                response.setComments(arrayList);
                response.setMessage("comment not found");
                response.setStatus(false);
                response.setStatusCode(1);
            }else{
                response.setComments(arrayList);
                response.setMessage("Get comment Successful ");
                response.setStatus(true);
                response.setStatusCode(0);
            }

        }

        else  if(request.getType().equals("notes")){

            ArrayList<NotesCommentModel> commentList = (ArrayList<NotesCommentModel>)
                    notesComentRepository.list("notes_id",request.getPost_id());
            commentList.forEach(model -> {
                CommentEntity entity = new CommentEntity();
                entity.setComment(model.getComment());
                entity.setComment_id(model.getComment_id());
            //    entity.setFname(model.getStud_name());
                entity.setPost_id(model.getNotes_id());
                StudentModel studentModel = studentRepository.find("id",Long.valueOf(model.getAdded_by())).firstResult();
                entity.setFname(studentModel.getFname());


                Integer integer = Math.toIntExact(model.getAdded_by());
                entity.setUser_id(integer);
                entity.setAdded_on(model.getAdded_on());
                entity.setTime(model.getAdded_on().toString());

                ArrayList<Comment_Reply_Model> rList =  getCommentReply(entity.getComment_id());
                entity.setReplyList(rList);

                arrayList.add(entity);

            });

            if(arrayList.isEmpty()){

                response.setComments(arrayList);
                response.setMessage("comment not found");
                response.setStatus(false);
                response.setStatusCode(1);
            }else{
                response.setComments(arrayList);
                response.setMessage("Get comment Successful");
                response.setStatus(true);
                response.setStatusCode(0);
            }
        }

        return  response;
    }

   ArrayList getCommentReply(int id){
     ArrayList<Comment_Reply_Model> list = (ArrayList<Comment_Reply_Model>)
             replyRepository.list("comment_id",id);

     return list;
   }

    Comment_Reply_Model getReply(long postID,CommentEntity entity){
        Comment_Reply_Model model = null;
        if(entity.getPost_id()==postID){
            model = new Comment_Reply_Model();
          return  model;
        }
        return  model;
    }

}
