package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.StudentTestModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentTestRepository implements PanacheRepository<StudentTestModel> {
}
