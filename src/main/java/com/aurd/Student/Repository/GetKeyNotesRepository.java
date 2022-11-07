package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.KeyNotes;

import com.aurd.Student.Model.Request.GetKeyNotesRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped

public class GetKeyNotesRepository implements PanacheRepository<KeyNotes>
{


//    public ArrayList getKeyNotesList(GetKeyNotesRequest request){
//        System.out.println(new Gson().toJson(request));
//
//        ArrayList<KeyNotesModel> arrayList = new ArrayList();
//        if(request.getSubjectId().equals(0) && request.getCourseId().equals(0)){
//
//            List<KeyNotesModel> list = list("student_id=?1 ORDER BY added_on DESC",request.getStudent_id());
//            arrayList.addAll(list);
//
//        } else if(!request.getCourseId().equals(0)&&! request.getSubjectId().equals(0)){
//         arrayList  = (ArrayList<KeyNotesModel>)
//                    list("student_id=?1 and course_id =?2 and subject_id = ?3 ORDER BY added_on DESC",request.getStudent_id(),request.getCourseId(),request.getSubjectId());
//
//        } else if(!request.getCourseId().equals(0) &&request.getSubjectId().equals(0)){
//            arrayList  = (ArrayList<KeyNotesModel>)
//                    list("student_id=?1 and course_id =?2 ORDER BY  added_on DESC",request.getStudent_id(),request.getCourseId());
//
//        }
//
//        else if(!request.getSubjectId().equals(0) && request.getCourseId().equals(0)){
//             arrayList  = (ArrayList<KeyNotesModel>)
//                    list("student_id=?1 and subject_id =?2 ORDER BY added_on DESC",request.getStudent_id(),request.getSubjectId());
//
//        }
//        return  arrayList;
//    }



}
