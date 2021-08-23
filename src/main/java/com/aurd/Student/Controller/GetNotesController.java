package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.NotesModel;
import com.aurd.Student.Model.Entity.TeacherModel;
import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.Entity.TopicModel;
import com.aurd.Student.Model.Request.GetNotesRequest;
import com.aurd.Student.Model.Response.GetNotesResponse;
import com.aurd.Student.Repository.NotesRepository;
import com.aurd.Student.Repository.TeacherRepository;
import com.aurd.Student.Repository.TopicsRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getNotes")

public class GetNotesController {


    @Inject
    NotesRepository repository;

    @Inject
    TeacherRepository teacherRepository;

    @Inject
    TopicsRepository topicsRepository;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    @Transactional

    public GetNotesResponse getNotes(GetNotesRequest request){


        ArrayList<NotesModel> arrayList = repository.getNotesList(request.getInst_id());
        ArrayList<NotesEntity> notesList = new ArrayList();
        NotesEntity notesEntity;
        for(int i=0;i<arrayList.size();i++){
            notesEntity  = new Gson().fromJson(new Gson().toJson(arrayList.get(i)),NotesEntity.class);
            TeacherModel teacherModel =teacherRepository.findTeacher(arrayList.get(i).getTeacher_id());
            System.out.println(teacherModel.getFname());

            TopicModel topicModel = topicsRepository.findTopicName(arrayList.get(i).getTopicId());

            notesEntity.setTeacherName(teacherModel.getFname());
            notesEntity.setTopic(topicModel.getTopic());

            notesList.add(notesEntity);

        }




        GetNotesResponse getNotesResponse = new GetNotesResponse();
        getNotesResponse.setNotes(notesList);
        getNotesResponse.setErrorCode(0);
        getNotesResponse.setMessage("Get notes success");
        getNotesResponse.setStatus(true);

        return  getNotesResponse;



    }

}
