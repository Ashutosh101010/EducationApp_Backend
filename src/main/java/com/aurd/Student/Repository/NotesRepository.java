package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.NotesModel;
import com.aurd.Student.Model.Entity.StudentNotesModel;
import com.aurd.Student.Model.Request.GetNotesRequest;
import com.aurd.Student.Model.Request.GetStudentNotesRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class NotesRepository implements PanacheRepository<NotesModel>
{


    public ArrayList getNotesList(int instID){
        ArrayList<NotesModel> arrayList = (ArrayList<NotesModel>) list("inst_id",instID);
        System.out.println("----------------------------------"+arrayList.size());
        return  arrayList;
    }



}
