package com.aurd.Student.Controller.comment;


import com.aurd.Student.Model.Entity.NotificationModel;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Request.CommentReplyRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.*;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;

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

@Path("addCommentReply")

public class CommentReplyController {

    @Inject
    CommentReplyRepository repository;

    @Inject
    NotificationRepository notificationRepository;

    @Inject
    StudentRepository studentRepository;

//    @Inject
//    StudentPostRepository postRepository;

    @Inject
    StudentPostCommentRepository studentPostCommentRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional


    public GeneralResponse CommentReply(CommentReplyRequest request) {

        System.out.println(new Gson().toJson(request));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        request.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        System.out.println(request);

        GeneralResponse response = new GeneralResponse();

        boolean value = repository.addCommentReplyRequest(request);
        if (value) {

       Student_Posts_Commented student_posts_commented =
                    studentPostCommentRepository.find("id",request.getComment_id()).firstResult();

            sentNotification(request.getUser_id(),Long.valueOf(student_posts_commented.getAdded_by()));

            response.seterrorCode(0);
            response.setStatus(true);
            response.setMessage("Comment Reply Added");
        } else {
            response.seterrorCode(1);
            response.setStatus(false);
            response.setMessage("Comments Reply Not Added");
        }

        return response;


    }

    public  void  sentNotification(Long added_by, Long posted_by) {

        StudentModel students = studentRepository.find("id", added_by).firstResult();
        System.out.println(new Gson().toJson(students));


        StudentModel model = studentRepository.find("id", posted_by).firstResult();
        System.out.println(new Gson().toJson(model));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();


        if (students.getId() != model.getId()) {


            String body = students.getFname() + " replied on your comment";
            String title = "Comment Added";
            NotificationModel notification = new NotificationModel();
            notification.setNotification_body(body);
            notification.setNotification_title(title);
            notification.setSender_id(added_by.intValue());
            notification.setReceiver_id(posted_by.intValue());
            notification.setTime(Timestamp.valueOf(sdf.format(calendar.getTime())));
            notification.setSender_type("Student");
            notification.setInst_id(students.getInst_id());

            System.out.println(model.getDeviceId());
            System.out.println();

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