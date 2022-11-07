package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Topic;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TopicsRepository implements PanacheRepository<Topic> {

//    public TopicModel findTopicName(long id){
//        return  find("id",id).firstResult();
//
//    }

}
