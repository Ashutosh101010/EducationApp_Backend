package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.LiveSessionModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class LiveSessionRepository implements PanacheRepository<LiveSessionModel> {


}
