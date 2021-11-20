package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.NotificationModel;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Entity.Student_Posts_Liked_Model;
import com.aurd.Student.Model.Request.LikeDislikeRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.NotificationRepository;
import com.aurd.Student.Repository.StudentPostLikedRepository;
import com.aurd.Student.Repository.StudentPostRepository;
import com.aurd.Student.Repository.StudentRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.aurd.Service.firebaseApp;
import static io.quarkus.hibernate.orm.panache.Panache.getEntityManager;

@Path("/likeDislike")
public class LikeDislikeController {
    @Inject
    StudentPostLikedRepository likedRepository;

    @Inject
    NotificationRepository notificationRepository;

    @Inject
    StudentPostRepository postRepository;

    @Inject
    StudentRepository studentRepository;

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



            StudentPostModel postModel  =
                    postRepository.find("id",Long.valueOf(request.getPostId())).firstResult();

            sentNotification(Long.valueOf(request.getStud_id()),Long.valueOf(postModel.getAdded_by()));



            response.setMessage("Post Liked");
            response.setStatus(true);
            response.seterrorCode(0);

        }else if(request.getOperation()==2){

            String dislike = "DELETE FROM student_posts_liked WHERE id=?";


            Query query = getEntityManager().createNativeQuery(dislike);
            query.setParameter(1,request.getId());
            query.executeUpdate();


            response.setMessage("Post Dislike");
            response.setStatus(true);
            response.seterrorCode(0);
        }else{
            response.setMessage("Unable to proceed");
            response.setStatus(false);
            response.seterrorCode(1);
        }

        return  response;

    }
    public  void  sentNotification(Long added_by, Long posted_by) {


        StudentModel students = studentRepository.find("id", added_by).firstResult();
        System.out.println(new Gson().toJson(students));


        StudentModel model = studentRepository.find("id", posted_by).firstResult();
        System.out.println(new Gson().toJson(model));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();


        if (students.getId() != model.getId()) {


            String body = students.getFname() + " liked your Doubt";
            String title = "Doubt liked";
            NotificationModel notification = new NotificationModel();
            notification.setNotification_body(body);
            notification.setNotification_title(title);
            notification.setSender_id(added_by.intValue());
            notification.setReceiver_id(posted_by.intValue());
            notification.setTime(Timestamp.valueOf(sdf.format(calendar.getTime())));
            notification.setSender_type("Student");
            notification.setInst_id(students.getInst_id());

            notificationRepository.insertNotification(notification);

            if(model.getDeviceId()!=null){
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

}
