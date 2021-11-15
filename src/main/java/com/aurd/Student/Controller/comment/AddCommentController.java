package com.aurd.Student.Controller.comment;


import com.aurd.Student.Model.Entity.NotificationModel;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Model.Students;
import com.aurd.Student.Repository.*;

import com.aurd.Student.Repository.comment.Blog_Comment_Repository;
import com.aurd.Student.Repository.comment.Current_Affair_Comment_Repository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.aurd.Service.firebaseApp;


@Path("/addComment")
public class AddCommentController {
    @Inject
    Blog_Comment_Repository blogCommentRepository;


    @Inject
    Current_Affair_Comment_Repository affair_comment_repository;

    @Inject
    StudentPostCommentRepository postCommentRepository;

    @Inject
    NotesCommentRepository notesComentRepository;

    @Inject
    StudentRepository studentRepository;


    @Inject
    StudentPostRepository postRepository;

    @Inject
    NotificationRepository notificationRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public GeneralResponse addComment(AddPostCommentRequest request) {


        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
request.setAdded_on(date);

        System.out.println(request);

        GeneralResponse response = new GeneralResponse();

        if (request.getType().equals("blog")) {
            boolean val = blogCommentRepository.addStudentBlogCommentRequest(request);
            if (val == true) {

                response.seterrorCode(0);
                response.setMessage("Comment added");
                response.setStatus(true);


            } else {
                response.seterrorCode(1);
                response.setMessage("Unable to add comment");
                response.setStatus(true);
            }
        } else if (request.getType().equals("currentAffair")) {
            boolean val = affair_comment_repository.addCurrentAffairCommentRequest(request);
            if (val == true) {

                response.seterrorCode(0);
                response.setMessage("Comment added");
                response.setStatus(true);
            } else {
                response.seterrorCode(1);
                response.setMessage("Unable to add comment");
                response.setStatus(true);
            }
        } else if (request.getType().equals("studentPost")) {
            boolean val = postCommentRepository.addPostCommentRequest(request);
            if (val == true) {

           StudentPostModel postModel  =
                   postRepository.find("id",Long.valueOf(request.getPost_id())).firstResult();

                sentNotification(request.getAdded_by(),Long.valueOf(postModel.getAdded_by()));

                response.seterrorCode(0);
                response.setMessage("Comment added");
                response.setStatus(true);
            } else {
                response.seterrorCode(1);
                response.setMessage("Unable to add comment");
                response.setStatus(true);
            }
        } else if (request.getType().equals("notes")) {
            boolean val = notesComentRepository.addNotesCommentRequest(request);
            if (val == true) {
                response.seterrorCode(0);
                response.setMessage("Comment added");
                response.setStatus(true);
            } else {
                response.seterrorCode(1);
                response.setMessage("Unable to add comment");
                response.setStatus(true);
            }
        }
        return response;

    }

        public  void sentNotification (Long added_by,Long posted_by){

            StudentModel students = studentRepository.find("id", added_by).firstResult();

            StudentModel model = studentRepository.find("id",posted_by).firstResult();


            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();


        if(students.getId()!=model.getId()){


            String body = students.getFname()+" commented on your doubt";
            String title = "Comment Added";
            NotificationModel notification = new NotificationModel();
            notification.setNotification_body(body);
            notification.setNotification_title(title);
            notification.setSender_id(added_by.intValue());
            notification.setReceiver_id(posted_by.intValue());
            notification.setTime(Timestamp.valueOf(sdf.format(calendar.getTime())));
            notification.setSender_type("Student");
            notification.setInst_id(students.getInst_id());

            notificationRepository.insertNotification(notification);
            Message message = Message.builder()
                    .setToken(model.getDeviceId())
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build()).build();

            try {
                System.out.println(message);
                FirebaseMessaging.getInstance(firebaseApp).send(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }




}
