package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Entity.StudentNotesModel;
import com.aurd.Student.Model.Request.AddKeyNotes;
import com.aurd.Student.Model.Request.AddNotesRequest;
import com.aurd.Student.Model.Request.GetStudentNotesRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.hibernate.query.Query;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class StudentNotesRepository implements PanacheRepository<StudentNotesModel> {

    public boolean addStudentNotes(AddNotesRequest request){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar now = Calendar.getInstance();
            System.out.println(sdf.format(now.getTime()));

            StudentNotesModel studentNotesModel = new Gson().fromJson(new Gson().toJson(request),
                    StudentNotesModel.class);
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
