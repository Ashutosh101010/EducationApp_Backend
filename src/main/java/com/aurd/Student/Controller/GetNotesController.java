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
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;

@Path("/notes/getInstituteNotes")

public class GetNotesController {


    @Inject
    NotesRepository repository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    @Transactional

    public GetNotesResponse getNotes(GetNotesRequest request){


        ArrayList<NotesEntity> notesList = new ArrayList();


        String string = "SELECT notes.name, notes.file , notes.subject_id,notes.id,notes.created_at, " +
                "employees.fname,topics.topic,notes.topicId FROM notes INNER JOIN employees ON " +
                "employees.id= notes.subject_id INNER JOIN topics ON topics.id= notes.topicId " +
                "WHERE notes.inst_id = ?";

        Query query = repository.getEntityManager().createNativeQuery(string);
        query.setParameter(1,request.getInst_id());

        ArrayList<Object[]> list = (ArrayList<Object[]>) query.getResultList();
        list.forEach(objects -> {
            NotesEntity notesEntity = new NotesEntity();
            notesEntity.setName(objects[0].toString());
            notesEntity.setFile(objects[1].toString());
            notesEntity.setTeacher_id(Integer.parseInt(objects[2].toString()));
            notesEntity.setId(Long.parseLong(objects[3].toString()));
            notesEntity.setCreated_at(Timestamp.valueOf(objects[4].toString()));
            notesEntity.setTeacherName(objects[5].toString());
            notesEntity.setTopic(objects[6].toString());
            notesEntity.setTopicId(Integer.parseInt(objects[7].toString()));

            notesList.add(notesEntity);
        });

        GetNotesResponse getNotesResponse = new GetNotesResponse();
        getNotesResponse.setNotes(notesList);
        getNotesResponse.setErrorCode(0);
        getNotesResponse.setMessage("Get notes success");
        getNotesResponse.setStatus(true);

        return  getNotesResponse;

    }

}
