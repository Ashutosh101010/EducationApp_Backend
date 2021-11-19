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
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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


    @POST


    public StudentPostCommentResponse getComment(GetCommentRequest request) {
        StudentPostCommentResponse response = new StudentPostCommentResponse();

        ArrayList<CommentEntity> arrayList = new ArrayList<>();

        if(request.getType().equals("blog")){

          ArrayList<Blog_Comment_Model> commentList = (ArrayList<Blog_Comment_Model>)
                  blog_repository.list("blog_id =?1 ORDER BY added_on DESC ",request.getPost_id());
          commentList.forEach(model -> {
              CommentEntity entity = new CommentEntity();
              entity.setComment(model.getComment());
              entity.setComment_id(model.getComment_id());
              entity.setPost_id(model.getBlog_id_id());
              entity.setUser_id(model.getAdded_by());
              entity.setAdded_on(model.getAdded_on());
              entity.setTime(model.getAdded_on().toString());

              if(model.getType().equals("student")){

                  entity.setFname(model.getStudentModel().getFname());
                  if(model.getStudentModel().getProfile()!=null){
                      entity.setImage(model.getStudentModel().getProfile());
                  }


              } else if(model.getType().equals("faculty")){
                  entity.setFname(model.getTeacherModel().getFname());
                  if(model.getTeacherModel().getProfile()!=null){
                      entity.setImage(model.getTeacherModel().getProfile());
                  }

              }
              ArrayList<Comment_Reply_Model> rList =  getCommentReply(entity);
              entity.setReplyList(rList);

              arrayList.add(entity);

          });

            response.setComments(arrayList);
            response.setMessage("Get comment Successful");
            response.setStatus(true);
            response.seterrorCode(0);

        }
        else if(request.getType().equals("currentAffair")){

            ArrayList<Current_AffairsCommented_Model> commentList =
                    (ArrayList<Current_AffairsCommented_Model>)
                    ca_repository.list("current_affair_id=?1 ORDER BY added_on DESC",request.getPost_id());
            commentList.forEach(model -> {
                CommentEntity entity = new CommentEntity();
                entity.setComment(model.getComment());
                entity.setComment_id(model.getComment_id());

                if(model.getType().equals("student")){

                    entity.setFname(model.getStudentModel().getFname());
                    if(model.getStudentModel().getProfile()!=null){
                        entity.setImage(model.getStudentModel().getProfile());
                    }

                } else if(model.getType().equals("faculty")){
                    entity.setFname(model.getTeacherModel().getFname());
                    if(model.getTeacherModel().getProfile()!=null){
                        entity.setImage(model.getTeacherModel().getProfile());
                    }
                }
                entity.setPost_id(model.getCurrent_affair_id());
                entity.setUser_id(model.getAdded_by());
                entity.setAdded_on(model.getAdded_on());
                entity.setTime(model.getAdded_on().toString());

                ArrayList<Comment_Reply_Model> rList =  getCommentReply(entity);
                entity.setReplyList(rList);

                arrayList.add(entity);

            });

            response.setComments(arrayList);
            response.setStatus(true);
            response.seterrorCode(0);
            response.setMessage("Get comment Successful");
        }
        else  if(request.getType().equals("studentPost")){

            ArrayList<Student_Posts_Commented> commentList = (ArrayList<Student_Posts_Commented>)
                    repository.list("post_id=?1 ORDER BY added_on DESC",request.getPost_id());
            commentList.forEach(model -> {
                CommentEntity entity = new CommentEntity();
                entity.setComment(model.getComment());
                entity.setComment_id(model.getComment_id());
                entity.setPost_id(model.getPost_id());

                if(model.getType().equals("student")){

                    entity.setFname(model.getStudentModel().getFname());
                    if(model.getStudentModel().getProfile()!=null){
                        entity.setImage(model.getStudentModel().getProfile());
                    }

                }  else if(model.getType().equals("faculty")){
                    entity.setFname(model.getTeacherModel().getFname());
                    if(model.getTeacherModel().getProfile()!=null){
                        entity.setImage(model.getTeacherModel().getProfile());
                    }
                }

               Integer integer = Math.toIntExact(model.getAdded_by());
                entity.setUser_id(integer);
                entity.setAdded_on(model.getAdded_on());
                entity.setTime(model.getAdded_on().toString());

                ArrayList<Comment_Reply_Model> rList =  getCommentReply(entity);
                entity.setReplyList(rList);

                arrayList.add(entity);

            });

            response.setComments(arrayList);
            response.setMessage("Get comment Successful ");
            response.setStatus(true);
            response.seterrorCode(0);

        }

        else  if(request.getType().equals("notes")){

            ArrayList<NotesCommentModel> commentList = (ArrayList<NotesCommentModel>)
                    notesComentRepository.list("notes_id=?1 ORDER BY added_on DESC",request.getPost_id());
            commentList.forEach(model -> {
                CommentEntity entity = new CommentEntity();
                entity.setComment(model.getComment());
                entity.setComment_id(model.getComment_id());
                entity.setPost_id(model.getNotes_id());

                if(model.getType().equals("student")){

                    entity.setFname(model.getStudentModel().getFname());
                    if(model.getStudentModel().getProfile()!=null){
                        entity.setImage(model.getStudentModel().getProfile());
                    }
                }else if(model.getType().equals("faculty")){
                    entity.setFname(model.getTeacherModel().getFname());
                    if(model.getTeacherModel().getProfile()!=null){
                        entity.setImage(model.getTeacherModel().getProfile());
                    }
                }


                Integer integer = Math.toIntExact(model.getAdded_by());
                entity.setUser_id(integer);
                entity.setAdded_on(model.getAdded_on());
                entity.setTime(model.getAdded_on().toString());

                ArrayList<Comment_Reply_Model> rList =  getCommentReply(entity);
                entity.setReplyList(rList);

                arrayList.add(entity);

            });
            response.setComments(arrayList);
            response.setMessage("Get comment Successful");
            response.setStatus(true);
            response.seterrorCode(0);
        }

        return  response;
    }

   ArrayList getCommentReply(CommentEntity entity){
     ArrayList<Comment_Reply_Model> list = (ArrayList<Comment_Reply_Model>)
             replyRepository.list("comment_id=?1 ORDER BY added_on DESC",entity.getComment_id());
     list.forEach(comment_reply_model -> {
//         System.out.println(new Gson().toJson(comment_reply_model));

         if(comment_reply_model.getUser_type().equals("faculty")){
             comment_reply_model.setFname(comment_reply_model.getTeacherModel().getFname());
             comment_reply_model.setTime(comment_reply_model.getAdded_on().toString());

             if(comment_reply_model.getTeacherModel().getProfile()!=null){
                 comment_reply_model.setImage(comment_reply_model.getTeacherModel().getProfile());
             }

         }else if(comment_reply_model.getUser_type().equals("student")){
             comment_reply_model.setFname(comment_reply_model.getStudentModel().getFname());
             comment_reply_model.setTime(comment_reply_model.getAdded_on().toString());
             if(comment_reply_model.getStudentModel().getProfile()!=null){
                 comment_reply_model.setImage(comment_reply_model.getStudentModel().getProfile());
             }
         }
     });

     return list;
   }

//    Comment_Reply_Model getReply(long postID,CommentEntity entity){
//        Comment_Reply_Model model = null;
//        if(entity.getPost_id()==postID){
//            model = new Comment_Reply_Model();
//          return  model;
//        }
//        return  model;
//    }

}
