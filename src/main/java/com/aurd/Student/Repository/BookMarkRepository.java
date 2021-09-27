package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.Blog_Liked_Model;
import com.aurd.Student.Model.Entity.BookMarkModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class BookMarkRepository implements PanacheRepository<BookMarkModel> {


}
