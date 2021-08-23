package com.aurd.Student.Repository.mapping;

import com.aurd.Student.Model.Entity.map.Map_Subject_SubSubject;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class MapSubject_SubSubject_Repository implements PanacheRepository<Map_Subject_SubSubject> {

    public ArrayList getSubSubjectsID(long subjectId){

      ArrayList<Map_Subject_SubSubject> arrayList = (ArrayList<Map_Subject_SubSubject>) find("subjectId",subjectId).list();
      for(Map_Subject_SubSubject model : arrayList){
          System.out.println("Sub subject Id"+model.getSubsubId());
          System.out.println(model.getId());

      }

      return  arrayList;

    }

}
