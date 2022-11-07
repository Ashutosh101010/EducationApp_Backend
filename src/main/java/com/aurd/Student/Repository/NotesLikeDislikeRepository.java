package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.NotesLiked;
import com.aurd.Student.Model.Request.NotesLikeDislikeRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;



@ApplicationScoped

public class NotesLikeDislikeRepository implements PanacheRepository<NotesLiked> {

//    public boolean addNotesLikeDislikeRequest(NotesLikeDislikeRequest request) {
//
//        Notes_Liked_Model notes_liked_model = new Gson().fromJson(new Gson().toJson(request), Notes_Liked_Model.class);
//        persist(notes_liked_model);
//
//
//        return true;
//    }


}
