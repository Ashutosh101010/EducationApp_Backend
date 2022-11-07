package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.SubSubject;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class SubSubject_Repository implements PanacheRepository<SubSubject> {

//    public ArrayList getSubSubjects(long subSubjectID,long instID){
//        ArrayList<SubSubjectModel> arrayList  = (ArrayList<SubSubjectModel>)
//                find("id =?1",subSubjectID).list();
//        for(int i=0;i<arrayList.size();i++){
//            System.out.println(arrayList.get(i).getSub_subject());
//        }
//
//
//        return  arrayList;
//    }

}
