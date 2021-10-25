package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.Comment_Reply_Model;
import com.aurd.Student.Model.Request.CommentReplyRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped

public class CommentReplyRepository implements PanacheRepository<Comment_Reply_Model> {


   public boolean addCommentReplyRequest(CommentReplyRequest  request){

      Comment_Reply_Model comment_reply_model = new Gson().
              fromJson(new Gson().toJson(request),Comment_Reply_Model.class);
      comment_reply_model.setUser_type("student");

      persist(comment_reply_model);

      return true;

       }
   }
