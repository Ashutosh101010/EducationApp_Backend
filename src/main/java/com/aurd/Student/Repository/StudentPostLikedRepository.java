package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.StudentPost;
import com.aurd.Student.Model.Entity.StudentPostsLiked;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentPostLikedRepository implements PanacheRepository<StudentPostsLiked> {
}
