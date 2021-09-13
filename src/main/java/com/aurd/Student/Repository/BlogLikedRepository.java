package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Blog_Liked_Model;
import com.aurd.Student.Model.Request.BlogLikeDislikeRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped

public class BlogLikedRepository implements PanacheRepository <Blog_Liked_Model> {

    public boolean addBlogLikedDislikeRequest(BlogLikeDislikeRequest request) {

        Blog_Liked_Model blog_liked_model = new Gson().fromJson(new Gson().toJson(request), Blog_Liked_Model.class);
        persist(blog_liked_model);


        return true;
    }
}