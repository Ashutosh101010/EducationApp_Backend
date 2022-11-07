package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Notes;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class NotesRepository implements PanacheRepository<Notes>
{


//    public ArrayList getNotesList(int instID){
//        ArrayList<NotesModel> arrayList = new ArrayList<>();
//        try{
//            arrayList = (ArrayList<NotesModel>) list("inst_id",instID);
//            return  arrayList;
//        }catch (Exception e){
//              return  arrayList;
//        }
//
//
//    }



}
