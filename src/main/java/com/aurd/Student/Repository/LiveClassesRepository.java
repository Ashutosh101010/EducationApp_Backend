package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.LiveClassModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Table;

@ApplicationScoped
public class LiveClassesRepository implements PanacheRepository<LiveClassModel> {




}
