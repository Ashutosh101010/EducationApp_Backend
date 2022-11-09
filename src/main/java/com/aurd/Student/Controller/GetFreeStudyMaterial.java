package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetFreeStudyMaterialRequest;
import com.aurd.Student.Model.Response.GetFreeStudyMaterialResponse;
import com.aurd.Student.Repository.*;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("/getFreeStudyMaterial")
public class GetFreeStudyMaterial {


  @Inject
    VideoLectureRepository videoLectureRepository;

  @Inject
    NotesRepository notesRepository;

  @Inject
  TeacherRepository teacherRepository;

  @Inject
  AdminRepository adminRepository;


  @Inject
  NotesCommentRepository notesCommentRepository;

  @Inject
  NotesLikeDislikeRepository notesLikeDislikeRepository;

  @Inject
  StudentCourseRepository studentCourseRepository;


  @Inject
  SubjectRepository subjectRepository;

  @Inject
  TopicsRepository topicsRepository;

  @Inject
  SubSubject_Repository subSubjectRepository;


    @POST
    public GetFreeStudyMaterialResponse getFreeStudyMaterial(GetFreeStudyMaterialRequest request)
    {

        GetFreeStudyMaterialResponse response=new GetFreeStudyMaterialResponse();
        List<VideoModel> videoModels=videoLectureRepository.getFreeVideos(request);
        List<NotesModel> notesModels=notesRepository.getFreeNotes(request);

        List<NotesEntity> notesEntityList=new ArrayList<>();
        notesModels.forEach(notesModel -> {
            NotesEntity notesEntity = new NotesEntity();
            notesEntity.setName(notesModel.getName());
            notesEntity.setFile(notesModel.getFile());
            notesEntity.setCreated_by(notesModel.getCreated_by());
            notesEntity.setId(notesModel.getId());
            notesEntity.setCreated_at(notesModel.getCreated_at());
//                notesEntity.setTeacherName(objects[5].toString());
            try {
                notesEntity.setSubSubject(subSubjectRepository.find("id", Long.parseLong(notesModel.getSub_subject_id())).firstResult().getSub_subject());
            }catch (Exception e)
            {
                notesEntity.setSubSubject("");
            }

            try{
                notesEntity.setTopic(topicsRepository.findTopicName(notesModel.getId()).getTopic());
            }catch (Exception e)
            {
                notesEntity.setTopic("");
            }

            notesEntity.setTopicId(notesModel.getTopicId());
            notesEntity.setDescription(notesModel.getDescription());
            notesEntity.setCourse_id(notesModel.getCourse_id().intValue());
            ;
            try{
                notesEntity.setSubject(subjectRepository.find("id",notesModel.getSubject_id()).firstResult().getSubject());

            }catch (Exception e)
            {
                notesEntity.setSubject("");

            }
             notesEntity.setCourse(notesModel.getCourseModel().getCourse());

                notesEntity.setFee_type(notesModel.getFee_type());


                notesEntity.setImage(notesModel.getFile());

            notesEntity.setTimeStamp(notesEntity.getCreated_at().getTime());


            if(notesModel.getUser_type().equals("employee")){
                TeacherModel teacherModel = teacherRepository.find("id",
                        notesEntity.getCreated_by().longValue()).firstResult();

                notesEntity.setTeacherName(teacherModel.getFname());
                notesEntity.setImage(teacherModel.getProfile());
            }else if(notesModel.getUser_type().equals("admin")){
                AdminModel adminModel = adminRepository.find("id",
                        notesEntity.getCreated_by()).firstResult();
                notesEntity.setTeacherName(adminModel.getName());
                notesEntity.setImage(adminModel.getProfile());

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

            Integer course = notesEntity.getCourse_id();

            StudentCourseModel studentCourseModel = studentCourseRepository.
                    find("userId=?1 and courseId =?2", Long.valueOf(request.getStudId()),course.intValue()).firstResult();
            if(studentCourseModel!=null){
                notesEntity.setPurchased(true);
            }else{
                notesEntity.setPurchased(false);
            }

            System.out.println(new Gson().toJson(notesEntity));



            notesEntityList.add(notesEntity);
        });

        response.setNotes(notesEntityList);
        response.setVideos(videoModels);
        response.setStatus(true);
        response.setErrorCode(0);
        return response;


    }
}
