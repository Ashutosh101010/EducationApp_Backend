package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.NotesCommentModel;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotesComentRepository implements PanacheRepository<NotesCommentModel> {


    public boolean addNotesCommentRequest(AddPostCommentRequest request) {
        NotesCommentModel commentModel  = new Gson().fromJson(new Gson().toJson(request),
                NotesCommentModel.class);
        commentModel.setNotes_id(request.getPost_id());
        persist(commentModel);
        return true;

    }
}
