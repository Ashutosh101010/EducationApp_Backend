package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.StudentNotes;

import com.aurd.Student.Model.Request.AddKeyNotes;
import com.aurd.Student.Model.Request.AddNotesRequest;
import com.aurd.Student.Model.Request.GetStudentNotesRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.hibernate.query.Query;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class StudentNotesRepository implements PanacheRepository<StudentNotes> {

    @Transactional
//
    public boolean addStudentNotes(AddNotesRequest request){
        try{

            StudentNotes studentNotes = new StudentNotes();
            studentNotes.setNote(request.getNote());
            studentNotes.set
            studentNotesModel.setAdded_on(Timestamp.valueOf(sdf.format(now.getTime())));
            studentNotesModel.setUpdated_on(Timestamp.valueOf(sdf.format(now.getTime())));

            persist(studentNotesModel);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


}
