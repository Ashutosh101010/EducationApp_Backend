package com.aurd.Student.Controller;

import com.aurd.Student.Constant.Constants;
import com.aurd.Student.Model.Entity.NotificationModel;
import com.aurd.Student.Model.Request.GetNotificationRequest;
import com.aurd.Student.Model.Response.GetNotificationResponse;
import com.aurd.Student.Repository.*;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getNotification")
public class GetNotificationController {


    @Inject
    NotificationRepository notificationRepository;

    @Inject
    BlogRepository blogRepository;

    @Inject
    CurrentAffairRepository currentAffairRepository;

    @Inject
    NotesRepository notesRepository;
    @Inject
    VideoLectureRepository videoLectureRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public GetNotificationResponse getNotification(GetNotificationRequest request){

      ArrayList<NotificationModel> list = (ArrayList<NotificationModel>) notificationRepository.
              list("inst_id=?1 and receiver_id=?2 or inst_id=?1 and receiver_id=0",request.getInst_id(),request.getStud_id());

      list.forEach(notificationModel -> {
          if(notificationModel.getType().equals(Constants.NotificatioType.blog.name()))
          {
              notificationModel.setBlog(blogRepository.findById(notificationModel.getEntityId()));

          }
          else if(notificationModel.getType().equals(Constants.NotificatioType.currentAffair.name()))
          {
              notificationModel.setCurrentAffair(currentAffairRepository.findById(notificationModel.getEntityId()));

          }
          else if(notificationModel.getType().equals(Constants.NotificatioType.video.name()))
          {
              notificationModel.setVideo(videoLectureRepository.findById(notificationModel.getEntityId()));

          }
          else if(notificationModel.getType().equals(Constants.NotificatioType.notes.name()))
          {
              notificationModel.setNotes(notesRepository.findById(notificationModel.getEntityId()));
          }
      });
      GetNotificationResponse response = new GetNotificationResponse();

      response.setNotifications(list);
      response.setErrorCode(0);
      response.setStatus(true);
      response.setMessage("Get Notification List");

      return  response;

    }

}
