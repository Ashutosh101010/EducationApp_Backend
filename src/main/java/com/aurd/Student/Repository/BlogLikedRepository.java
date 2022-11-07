package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.BlogLiked;

import com.aurd.Student.Model.Request.BlogLikeDislikeRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped

public class BlogLikedRepository implements PanacheRepository <BlogLiked> {


}