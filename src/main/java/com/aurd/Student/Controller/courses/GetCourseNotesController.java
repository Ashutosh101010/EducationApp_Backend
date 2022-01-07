package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.Entity.AdminModel;
import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.TeacherModel;
import com.aurd.Student.Model.Entity.TopicModel;
import com.aurd.Student.Model.Request.GetNotesRequest;
import com.aurd.Student.Model.Response.GetNotesResponse;
import com.aurd.Student.Repository.*;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@Path("/courses/getCourseNotes")
public class GetCourseNotesController {

    @Inject
    NotesRepository repository;

    @Inject
    NotesLikeDislikeRepository notesLikeDislikeRepository;

    @Inject
    NotesCommentRepository notesCommentRepository;


    @Inject
    SubjectRepository subjectRepository;

    @Inject
    SubSubject_Repository subSubject_repository;

    @Inject
    TopicsRepository topicsRepository;

    @Inject
    CoursesRepository coursesRepository;
    @Inject
    TeacherRepository teacherRepository;

    @Inject
    AdminRepository adminRepository;



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    @Transactional

    public GetNotesResponse getNotes(GetNotesRequest request){


        ArrayList<NotesEntity> notesList = new ArrayList();
        ArrayList<Object[]> list = null;

        System.out.println(request);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();

        if(request.getFilter()==null ||request.getFilter().isEmpty() || request.getFilter().equals("")) {

            String lastId = "";
            if( request.getLastId()==null || request.getLastId().equals("")){
                lastId = sdf.format(calendar.getTime());
            }else{
                lastId = request.getLastId();
            }

            String string = "SELECT notes.name, notes.file, notes.created_by, notes.id, notes.created_at," +
                    "topics.topic, notes.topicId, notes.description," +
                    " notes.subject_id,notes.sub_subject_id, notes.course_id," +
                    " subjects.subject, courses.course, notes.fee_type, notes.user_type" +
                    " FROM notes " +
                    "INNER JOIN topics ON topics.id= notes.topicId INNER JOIN subjects ON " +
                    "subjects.id = notes.subject_id INNER JOIN courses ON courses.id = notes.course_id" +
                    " WHERE notes.inst_id = ? and notes.topicId = ? and notes.created_at<?" +
                    "  ORDER BY created_at DESC";

            Query query = repository.getEntityManager().createNativeQuery(string);
            query.setParameter(1,request.getInst_id());
            query.setParameter(2,request.getTopicId());
            query.setParameter(3,Timestamp.valueOf(lastId));

            list = (ArrayList<Object[]>) query.setMaxResults(5).getResultList();
        }else{
            String lastId = "";
            if(request.getLastId().equals("")){
                lastId = sdf.format(calendar.getTime());
            }else{
                lastId = request.getLastId();
            }
            String string = "SELECT notes.name, notes.file, notes.created_by, notes.id, notes.created_at," +
                    ",topics.topic, notes.topicId, notes.description," +
                    " notes.subject_id,notes.sub_subject_id, notes.course_id," +
                    " subjects.subject, courses.course, notes.fee_type, notes.user_type" +
                    " FROM notes  " +
                    "INNER JOIN topics ON topics.id= notes.topicId INNER JOIN subjects ON " +
                    "subjects.id = notes.subject_id INNER JOIN courses ON courses.id = notes.course_id" +
                    " WHERE notes.inst_id = ? and notes.topicId = ? and notes.fee_type=?" +
                    "and notes.created_at<?" +
                    " ORDER BY created_at DESC ";

            Query query = repository.getEntityManager().createNativeQuery(string);
            query.setParameter(1,request.getInst_id());
            query.setParameter(2,request.getTopicId());
            if(request.getFilter().equals("free")){
                query.setParameter(3,"free");
            }else{
                query.setParameter(3,"paid");
            }
            query.setParameter(4,Timestamp.valueOf(lastId));

            list = (ArrayList<Object[]>) query.setMaxResults(5).getResultList();
        }



        list.forEach(objects -> {
            NotesEntity notesEntity = new NotesEntity();


            notesEntity.setName(objects[0].toString());
            notesEntity.setFile(objects[1].toString());
            notesEntity.setCreated_by(Integer.parseInt(objects[2].toString()));
            notesEntity.setId(Long.parseLong(objects[3].toString()));
            notesEntity.setCreated_at(Timestamp.valueOf(objects[4].toString()));
//            notesEntity.setTeacherName(objects[5].toString());
            notesEntity.setTopic(objects[5].toString());
            notesEntity.setTopicId(Integer.parseInt(objects[6].toString()));
            notesEntity.setDescription(objects[7].toString());
            notesEntity.setSubject(objects[11].toString());
            notesEntity.setCourse(objects[12].toString());
            if(objects[13]==null){
                notesEntity.setFee_type("Free");
            }else{
                notesEntity.setFee_type(objects[13].toString());
            }

            notesEntity.setTimeStamp(notesEntity.getCreated_at().getTime());


            if(objects[14].toString().equals("employee")){
                TeacherModel teacherModel = teacherRepository.find("id",
                        notesEntity.getCreated_by().longValue()).firstResult();

                notesEntity.setTeacherName(teacherModel.getFname());
                if(teacherModel.getProfile()!=null){
                    notesEntity.setImage(teacherModel.getProfile());
                }

            }else if(objects[14].toString().equals("admin")){
                AdminModel adminModel = adminRepository.find("id",notesEntity.getCreated_by()).firstResult();
                notesEntity.setTeacherName(adminModel.getName());
                if(adminModel.getProfile()!=null){
                    notesEntity.setImage(adminModel.getProfile());
                }
            }



            String commentQuery = "SELECT COUNT(*) FROM `notes_comment` WHERE notes_id =? ";
            Query comment = notesCommentRepository.getEntityManager().createNativeQuery(commentQuery);
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
