package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.Entity.TeacherModel;
import com.aurd.Student.Model.Entity.TopicModel;
import com.aurd.Student.Model.Request.GetNotesRequest;
import com.aurd.Student.Model.Response.GetNotesResponse;
import com.aurd.Student.Repository.NotesCommentRepository;
import com.aurd.Student.Repository.NotesLikeDislikeRepository;
import com.aurd.Student.Repository.NotesRepository;
import com.aurd.Student.Repository.TeacherRepository;

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

@Path("/notes/getInsituteNotes")

public class GetNotesController {


    @Inject
    NotesRepository repository;

    @Inject
    NotesLikeDislikeRepository notesLikeDislikeRepository;

    @Inject
    TeacherRepository teacherRepository;

    @Inject
    NotesCommentRepository notesComentRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    @Transactional

    public GetNotesResponse getNotes(GetNotesRequest request){



        ArrayList<NotesEntity> notesList = new ArrayList();
        ArrayList<Object[]> list;

        if(request.getFilter()==null ||request.getFilter().isEmpty() || request.getFilter().equals("")){
            String string = "SELECT notes.name, notes.file, notes.created_by, notes.id, notes.created_at, " +
                    "employees.fname,topics.topic, notes.topicId, notes.description, notes.subject_id," +
                    " notes.sub_subject_id, notes.course_id, subjects.subject, courses.course, notes.fee_type FROM notes" +
                    " INNER JOIN employees ON employees.id= notes.created_by INNER JOIN topics " +
                    "ON topics.id= notes.topicId INNER JOIN subjects ON subjects.id = notes.subject_id " +
                    "INNER JOIN courses ON courses.id = notes.course_id " +
                    "WHERE notes.inst_id = ?";

            Query query = repository.getEntityManager().createNativeQuery(string);
            query.setParameter(1,request.getInst_id());
            list = (ArrayList<Object[]>) query.getResultList();
        }else{
            String  string = "SELECT notes.name, notes.file, notes.created_by, notes.id, notes.created_at," +
                    " employees.fname,topics.topic, notes.topicId, notes.description, notes.subject_id," +
                    " notes.sub_subject_id, notes.course_id, subjects.subject, courses.course," +
                    " notes.fee_type " +
                    "FROM notes INNER JOIN employees ON employees.id= notes.created_by INNER JOIN" +
                    " topics ON topics.id= notes.topicId INNER JOIN subjects ON subjects.id = notes.subject_id " +
                    "INNER JOIN courses ON courses.id = notes.course_id " +
                    "WHERE notes.inst_id = ? and notes.fee_type=? ";

            Query query = repository.getEntityManager().createNativeQuery(string);
            query.setParameter(1,request.getInst_id());
            query.setParameter(2,request.getFilter());
            list = (ArrayList<Object[]>) query.getResultList();
        }


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
            notesEntity.setDescription(objects[8].toString());
            notesEntity.setSubject(objects[12].toString());
            notesEntity.setCourse(objects[13].toString());
            notesEntity.setFee_type(objects[14].toString());


//            if(objects[14].equals(null)){
//                notesEntity.setFee_type(null);
//            }else{
//                notesEntity.setFee_type(objects[14].toString());
//            }



            TeacherModel teacherModel = teacherRepository.find("id",
                    notesEntity.getCreated_by().longValue()).firstResult();

            notesEntity.setTeacherName(teacherModel.getFname());

            String commentQuery = "SELECT COUNT(*) FROM `notes_comment` WHERE notes_id =? ";
            Query comment = notesComentRepository.getEntityManager().createNativeQuery(commentQuery);
            comment.setParameter(1,notesEntity.getId());
            Integer commentCount = ((Number) comment.getSingleResult()).intValue();
            notesEntity.setComment(commentCount.longValue());


            String likeQuery = "SELECT * FROM `notes_liked` WHERE notes_id =?";
            Query like = notesLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
            like.setParameter(1,notesEntity.getId());
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
