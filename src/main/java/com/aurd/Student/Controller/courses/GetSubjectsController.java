package com.aurd.Student.Controller.courses;


import com.aurd.Student.Model.BeanClass.SubjectsEntity;
import com.aurd.Student.Model.Entity.SubSubjectModel;
import com.aurd.Student.Model.Entity.SubjectModel;
import com.aurd.Student.Model.Entity.TopicModel;
import com.aurd.Student.Model.Entity.map.Map_Subject_SubSubject;
import com.aurd.Student.Model.Request.GetInstituteSubjectRequest;
import com.aurd.Student.Model.Response.SubjectResponse;
import com.aurd.Student.Repository.SubjectRepository;
import com.aurd.Student.Repository.mapping.MapSubject_SubSubject_Repository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/courses/getCourseSubjects")
public class GetSubjectsController {


    @Inject
    SubjectRepository subjectRepository;

    @POST

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    @Transactional

    public SubjectResponse getSubjects(GetInstituteSubjectRequest request){

       ArrayList<SubjectsEntity>  mainList = new ArrayList<>();
//
//       for(int i=0;i<arrayList.size();i++){
//           SubjectsEntity subjectsEntity = new Gson().fromJson(new Gson().toJson(arrayList.get(i)),
//                   SubjectsEntity.class);
//
//        ArrayList<Map_Subject_SubSubject> subSubjectIDList =   mapSubject_subSubject_repository.getSubSubjectsID(arrayList.get(i).getId());
//        for(Map_Subject_SubSubject subSubjectModel :subSubjectIDList){
//         ArrayList<SubSubjectModel> list =  subSubject_repository.getSubSubjects(subSubjectModel.getSubsubId(),request.getInstID());
//         subjectsEntity.setArrayList(list);
//
//        }
//
//        mainList.add(subjectsEntity);
//       }


        String string = "SELECT subject_sub_subjects.subjectId,subject_sub_subjects.subsubId,subjects.subject,sub_subjects.sub_subject,subject_topics.topicId,topics.topic" +
                " FROM subject_sub_subjects INNER JOIN subjects ON subjects.id=subject_sub_subjects.subjectId " +
                "Inner JOIN sub_subjects ON subject_sub_subjects.subsubId=sub_subjects.id " +
                "INNER JOIN subject_topics on subject_topics.subjectId=sub_subjects.id" +
                " INNER JOIN topics ON topics.id=subject_topics.topicId where subjects.id in (select subjectId from course_subjects where course_subjects.courseId =? )";

        Query query = subjectRepository.getEntityManager().createNativeQuery(string);
        query.setParameter(1,request.getCourseID());

       ArrayList<Object[]> arrayList = (ArrayList<Object[]>) query.getResultList();
       ArrayList<SubjectsEntity> subjectsList = new ArrayList<>();

//        ArrayList<Map_Subject_SubSubject> subSubjectIDList =   mapSubject_subSubject_repository.getSubSubjectsID(arrayList.get(i).getId());
//        for(Map_Subject_SubSubject subSubjectModel :subSubjectIDList){
//         ArrayList list =  subSubject_repository.getSubSubjects(subSubjectModel.getSubsubId(),request.getInstID());
//         subjectsEntity.setSubjectList(list);
//        ArrayList<SubjectsEntity> subjects =new ArrayList<>();
       arrayList.forEach(o -> {


          SubjectsEntity subjectsEntity= getSubject(subjectsList
                  ,Long.parseLong(o[0].toString()));
          if(subjectsEntity==null)
          {
              subjectsEntity=new SubjectsEntity();
              subjectsEntity.setSubject(o[2].toString());
              subjectsEntity.setId(Long.parseLong(o[0].toString()));
              ArrayList<SubSubjectModel> subSubjectModelArrayList = new ArrayList<>();
              SubSubjectModel subSubjectModel=new SubSubjectModel();
              subSubjectModel.setId(Long.parseLong(o[1].toString()));
              subSubjectModel.setSub_subject(o[3].toString());

              ArrayList<TopicModel> topicList=new ArrayList<>();
              TopicModel topicModel=new TopicModel();
              topicModel.setId(Long.parseLong(o[4].toString()));
              topicModel.setTopic(o[5].toString());
              topicList.add(topicModel);
              subSubjectModel.setTopicList(topicList);
              subSubjectModelArrayList.add(subSubjectModel);
              subjectsEntity.setSubSubjectList(subSubjectModelArrayList);

              subjectsList.add(subjectsEntity);

          }else{

              SubSubjectModel subSubjectModel=getSubSubject(subjectsEntity.getSubSubjectList(),
                      Long.parseLong(o[1].toString()));

              if(subSubjectModel==null)
              {
                  subSubjectModel=new SubSubjectModel();
                  subSubjectModel.setId(Long.parseLong(o[1].toString()));
                  subSubjectModel.setSub_subject(o[3].toString());
                  ArrayList<TopicModel> topicList=new ArrayList<>();
                  TopicModel topicModel=new TopicModel();
                  topicModel.setId(Long.parseLong(o[4].toString()));
                  topicModel.setTopic(o[5].toString());
                  topicList.add(topicModel);
                  subSubjectModel.setTopicList(topicList);
                  subjectsEntity.getSubSubjectList().add(subSubjectModel);
              }
              else{
                  TopicModel topicModel=new TopicModel();
                  topicModel.setId(Long.parseLong(o[4].toString()));
                  topicModel.setTopic(o[5].toString());
                  subSubjectModel.getTopicList().add(topicModel);
              }
          }



       });




       SubjectResponse subjectResponse = new SubjectResponse();
       subjectResponse.setSubjectsList(subjectsList);
//       subjectResponse.setList(arrayList);
       subjectResponse.setStatus(true);
       subjectResponse.seterrorCode(0);
       subjectResponse.setMessage("Get Subject Successfully");

       return  subjectResponse;

    }


    SubjectsEntity getSubject(ArrayList<SubjectsEntity> list,long subjectId){
       SubjectsEntity subjectModel=null;
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getId()==subjectId)
            {
                subjectModel=list.get(i);
                break;
            }
        }
        return subjectModel;
    }
    SubSubjectModel getSubSubject(ArrayList<SubSubjectModel> list,long subSubjectId){
       SubSubjectModel subSubjectModel=null;
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getId()==subSubjectId)
            {
                subSubjectModel=list.get(i);
                break;
            }
        }
        return subSubjectModel;
    }
}



