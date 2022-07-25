package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.DigitalWorld;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DigitalWorldFolderRepository implements PanacheRepository<DigitalWorld> {


}
