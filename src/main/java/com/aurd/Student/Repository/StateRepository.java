package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.State;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class StateRepository implements PanacheRepository<State> {


//    public List getState() {
//
//        List<StateModel> list = listAll();
//
//
//
//        return list;
//
//
//    }
}
