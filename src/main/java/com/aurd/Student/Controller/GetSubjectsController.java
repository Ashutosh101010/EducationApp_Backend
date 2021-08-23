package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.SubjectsEntity;
import com.aurd.Student.Model.Entity.SubSubjectModel;
import com.aurd.Student.Model.Entity.SubjectModel;
import com.aurd.Student.Model.Entity.map.Map_Subject_SubSubject;
import com.aurd.Student.Model.Request.GetInstituteSubjectRequest;
import com.aurd.Student.Model.Response.SubjectResponse;
import com.aurd.Student.Repository.SubSubject_Repository;
import com.aurd.Student.Repository.SubjectRepository;
import com.aurd.Student.Repository.mapping.MapSubject_SubSubject_Repository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getInstituteSubjects")
public class GetSubjectsController {


    @Inject
    SubjectRepository subjectRepository;

    @Inject
    MapSubject_SubSubject_Repository mapSubject_subSubject_repository;

    @Inject
    SubSubject_Repository subSubject_repository;

    @POST

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    @Transactional

    public SubjectResponse getSubjects(GetInstituteSubjectRequest request){
       ArrayList<SubjectModel> arrayList = subjectRepository.getSubjects(request.getInstID());
       System.out.println(arrayList.size());

       ArrayList<SubjectsEntity>  mainList = new ArrayList<>();

       for(int i=0;i<arrayList.size();i++){
           SubjectsEntity subjectsEntity = new Gson().fromJson(new Gson().toJson(arrayList.get(i)),
                   SubjectsEntity.class);

        ArrayList<Map_Subject_SubSubject> subSubjectIDList =   mapSubject_subSubject_repository.getSubSubjectsID(arrayList.get(i).getId());
        for(Map_Subject_SubSubject subSubjectModel :subSubjectIDList){
         ArrayList<SubSubjectModel> list =  subSubject_repository.getSubSubjects(subSubjectModel.getSubsubId(),request.getInstID());
         subjectsEntity.setArrayList(list);

        }

           mainList.add(subjectsEntity);
       }

       SubjectResponse subjectResponse = new SubjectResponse();
       subjectResponse.setSubjectsList(mainList);
       subjectResponse.setStatus(true);
       subjectResponse.setStatusCode(0);
       subjectResponse.setMessage("Get Subject Successfully");

       return  subjectResponse;


    }

}



