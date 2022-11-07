package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Notification;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class NotificationRepository implements PanacheRepository<Notification> {

//    public void insertNotification(NotificationModel model){
//        persist(model);
//    }
//
//
//    public void getNotification(){
//
//    }


}
