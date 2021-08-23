package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.SubjectModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class SubjectRepository implements PanacheRepository<SubjectModel> {

    public ArrayList getSubjects(int instID){

      ArrayList<SubjectModel> subjectList = (ArrayList<SubjectModel>) find("inst_id",instID).list();
      return  subjectList;
    }




}
