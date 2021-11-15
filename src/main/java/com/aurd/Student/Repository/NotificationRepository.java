package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.NotificationModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class NotificationRepository implements PanacheRepository<NotificationModel> {

    public void insertNotification(NotificationModel model){
        persist(model);
    }


    public void getNotification(){

    }


}
