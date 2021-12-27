package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.AdminModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdminRepository  implements PanacheRepository<AdminModel> {



}
