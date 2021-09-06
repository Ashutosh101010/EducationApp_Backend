package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.KeyNotesModel;


import com.aurd.Student.Model.Request.GetKeyNotesRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped

public class GetKeyNotesRepository implements PanacheRepository<KeyNotesModel>
{


    public ArrayList getKeyNotesList(GetKeyNotesRequest request){
        ArrayList<KeyNotesModel> arrayList = new ArrayList();
        List<KeyNotesModel> list = list("student_id",request.getStudent_id());
        arrayList.addAll(list);
        return  arrayList;
    }



}
