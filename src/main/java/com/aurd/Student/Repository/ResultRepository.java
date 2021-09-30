package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.SaveResultModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResultRepository implements PanacheRepository<SaveResultModel> {
}
