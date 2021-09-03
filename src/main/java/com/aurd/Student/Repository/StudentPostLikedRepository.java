package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Student_Posts_Liked_Model;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentPostLikedRepository implements PanacheRepository<Student_Posts_Liked_Model> {
}
