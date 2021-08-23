package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Entity.StudentNotesModel;
import com.aurd.Student.Model.Request.AddNotesRequest;
import com.aurd.Student.Model.Request.GetStudentNotesRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.hibernate.query.Query;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class StudentNotesRepository implements PanacheRepository<StudentNotesModel> {

    public ArrayList getStudentNotes(GetStudentNotesRequest request){

        Map<String,Object> map = new HashMap();
        map.put("inst_id",request.getInst_id());
        map.put("stud_id",request.getStud_id());

        ArrayList<StudentNotesModel> arrayList = new ArrayList();
        List<StudentNotesModel> list=find("inst_id=?1 and stud_id =?2 ",request.getInst_id(),request.getStud_id()).list();
        arrayList.addAll(list);
        System.out.println(list);



        return  arrayList;
    }



    public boolean addStudentNotes(AddNotesRequest request){
        StudentNotesModel studentNotesModel = new Gson().fromJson(new Gson().toJson(request),StudentNotesModel.class);
        persist(studentNotesModel);
        return true;
    }

}
