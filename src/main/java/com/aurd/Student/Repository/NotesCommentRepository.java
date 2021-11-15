package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.NotesCommentModel;
import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.aurd.Student.Model.Request.GetCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

//    public ArrayList<NotesCommentModel> getComment(GetCommentRequest request) {
//
//    //   Query query = getEntityManager().createQuery("select NotesCommentModel  from  NotesCommentModel notes join" +
//    //            " StudentModel student on notes.added_by=student.id where notes.post_id=:postId");
//
//        Query query=getEntityManager().createQuery("select NotesCommentModel from NotesCommentModel notes" +
//                " left outer join TeacherModel Teacher on notes.added_by=Teacher.id" +
//                " left  outer join StudentModel Students on Students.id=notes.added_by where notes.notes_id=:postId");
//
//
//          query.setParameter("postId", request.getPost_id());
//
//        return (ArrayList<NotesCommentModel>) query.getResultList();
//    }

      //  Query query = getEntityManager().createQuery("select NotesCommentModel from NotesComm")
}
