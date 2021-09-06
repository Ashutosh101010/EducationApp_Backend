package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.BookMarkModel;

import com.aurd.Student.Model.Request.GetBookMarkRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class BookMarkRepository implements PanacheRepository<BookMarkModel> {

}
