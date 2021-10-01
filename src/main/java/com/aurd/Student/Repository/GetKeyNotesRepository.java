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
        if(request.getSubjectId().equals(0) && request.getCourseId().equals(0)){

            List<KeyNotesModel> list = list("student_id=?1 ORDER BY created_at",request.getStudent_id());
            arrayList.addAll(list);

        } else if(!request.getCourseId().equals(0)&&! request.getSubjectId().equals(0)){
         arrayList  = (ArrayList<KeyNotesModel>)
                    list("student_id=?1 and course_id =?2 and subject_id = ?3",request.getStudent_id(),request.getCourseId(),request.getSubjectId());

        } else if(!request.getSubjectId().equals(0) && request.getCourseId().equals(0)){
             arrayList  = (ArrayList<KeyNotesModel>)
                    list("student_id=?1 and subject_id =?2",request.getStudent_id(),request.getSubjectId());

        }else if(!request.getCourseId().equals(0) &&request.getSubjectId().equals(0)){
            arrayList  = (ArrayList<KeyNotesModel>)
                    list("student_id=?1 and course_id =?2",request.getStudent_id(),request.getCourseId());

        }

        return  arrayList;
    }



}
