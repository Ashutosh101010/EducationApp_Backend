package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.NotesCommentModel;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@ApplicationScoped
public class NotesCommentRepository implements PanacheRepository<NotesCommentModel> {


    public boolean addNotesCommentRequest(AddPostCommentRequest request) {
        NotesCommentModel commentModel  = new Gson().fromJson(new Gson().toJson(request),
                NotesCommentModel.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        commentModel.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        commentModel.setNotes_id(request.getPost_id());
        commentModel.setType("student");

        persist(commentModel);
        return true;

    }
}
