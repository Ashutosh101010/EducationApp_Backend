package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.NotificationModel;
import com.aurd.Student.Model.Request.GetNotificationRequest;
import com.aurd.Student.Model.Response.GetNotificationResponse;
import com.aurd.Student.Repository.NotificationRepository;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public GetNotificationResponse getNotification(GetNotificationRequest request){

      ArrayList<NotificationModel> list = (ArrayList<NotificationModel>) notificationRepository.
              list("inst_id=?1 and receiver_id=?2 or inst_id=?1 and receiver_id is null",request.getInst_id(),request.getStud_id());
      GetNotificationResponse response = new GetNotificationResponse();

      response.setNotifications(list);
      response.setErrorCode(0);
      response.setStatus(true);
      response.setMessage("Get Notification List");

      return  response;

    }

}
