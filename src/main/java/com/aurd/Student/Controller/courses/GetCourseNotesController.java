package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.Request.GetNotesRequest;
import com.aurd.Student.Model.Response.GetNotesResponse;
import com.aurd.Student.Repository.NotesComentRepository;
import com.aurd.Student.Repository.NotesLikeDislikeRepository;
import com.aurd.Student.Repository.NotesRepository;

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

@Path("/courses/getCourseNotes")
public class GetCourseNotesController {

    @Inject
    NotesRepository repository;

    @Inject
    NotesLikeDislikeRepository notesLikeDislikeRepository;

    @Inject
    NotesComentRepository notesComentRepository;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    @Transactional

    public GetNotesResponse getNotes(GetNotesRequest request){


        ArrayList<NotesEntity> notesList = new ArrayList();


        String string = "SELECT notes.name, notes.file , notes.created_by,notes.id,notes.created_at," +
                "employees.fname,topics.topic,notes.topicId FROM notes " +
                "INNER JOIN employees ON employees.id= notes.created_by " +
                "INNER JOIN topics ON topics.id= notes.topicId WHERE notes.topicId = ? AND notes.inst_id =?";

        Query query = repository.getEntityManager().createNativeQuery(string);
        query.setParameter(1,request.getTopicId());
        query.setParameter(2,request.getInst_id());

        ArrayList<Object[]> list = (ArrayList<Object[]>) query.getResultList();
        list.forEach(objects -> {
            NotesEntity notesEntity = new NotesEntity();
            notesEntity.setName(objects[0].toString());
            notesEntity.setFile(objects[1].toString());
            notesEntity.setCreated_by(Integer.parseInt(objects[2].toString()));
            notesEntity.setId(Long.parseLong(objects[3].toString()));
            notesEntity.setCreated_at(Timestamp.valueOf(objects[4].toString()));
            notesEntity.setTeacherName(objects[5].toString());
            notesEntity.setTopic(objects[6].toString());
            notesEntity.setTopicId(Integer.parseInt(objects[7].toString()));


            String commentQuery = "SELECT COUNT(*) FROM `notes_comment` WHERE notes_id =? ";
            Query comment = notesComentRepository.getEntityManager().createNativeQuery(commentQuery);
            comment.setParameter(1,Long.parseLong(objects[3].toString()));
            Integer commentCount = ((Number) comment.getSingleResult()).intValue();
            notesEntity.setComment(commentCount.longValue());


            String likeQuery = "SELECT * FROM `notes_liked` WHERE notes_id =?";
            Query like = notesLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
            like.setParameter(1,Long.parseLong(objects[3].toString()));
            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
            likeList.forEach(likeObject -> {
                if(request.getStudId() == Long.parseLong(likeObject[1].toString())){
                    System.out.println("Liked");
                    notesEntity.setLiked(true);
                }
            });

            Integer likeCount =  likeList.size();
            notesEntity.setLike(likeCount.longValue());


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
