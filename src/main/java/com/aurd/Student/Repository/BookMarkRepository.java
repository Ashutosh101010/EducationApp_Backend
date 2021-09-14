package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.Blog_Liked_Model;
import com.aurd.Student.Model.Entity.BookMarkModel;

import com.aurd.Student.Model.Request.BlogLikeDislikeRequest;
import com.aurd.Student.Model.Request.GetBookMarkRequest;
import com.aurd.Student.Model.Request.InsertBookMarkRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class BookMarkRepository implements PanacheRepository<BookMarkModel> {



    public boolean addInsertBookMarkRequest(InsertBookMarkRequest request) {

        BookMarkModel bookMarkModel = new Gson().fromJson(new Gson().toJson(request), BookMarkModel.class);
        persist(bookMarkModel);


        return true;
    }

}
