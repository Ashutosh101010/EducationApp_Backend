package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.BeanClass.CourseEntity;
import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.BeanClass.StudentCourseEntity;
import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.NotesModel;
import com.aurd.Student.Model.Entity.RunningBatchesModel;
import com.aurd.Student.Model.Entity.StudentCourseModel;
import com.aurd.Student.Model.Request.GetCoursesRequest;
import com.aurd.Student.Model.Request.GetStudentCourseRequest;
import com.aurd.Student.Model.Response.GetCoursesResponse;
import com.aurd.Student.Model.Response.GetStudentCourseResponse;
import com.aurd.Student.Repository.*;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/courses/getCourses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetCoursesController {

    @Inject
    CoursesRepository repository;

    @Inject
    RunningBatchRepository runningBatchRepository;
    
    @Inject
    NotesRepository notesRepository;

    @Inject
    VideoLectureRepository videoLectureRepository;


    @Inject
    StudentCourseRepository studentCourseRepository;

    @Inject
    QuizRepository quizRepository;


    @POST
    public GetCoursesResponse getCourses(GetCoursesRequest request){
        System.out.println(new Gson().toJson(request));
        GetCoursesResponse getCoursesResponse = new GetCoursesResponse();
      ArrayList<CourseModel> courseModelArrayList = repository.getCourses(request.getInst_id());
      ArrayList<CourseEntity> courses = new ArrayList<>();

        for(int i=0;i<courseModelArrayList.size();i++){
            CourseEntity courseEntity  = new Gson().fromJson
                  (new Gson().toJson(courseModelArrayList.get(i)),CourseEntity.class);

          StudentCourseModel courseModel = studentCourseRepository.find("courseId=?1 and userId=?2",
                  courseEntity.getId(),request.getStud_id()).firstResult();

          if(courseModel!=null){
              courseEntity.setPurchased(true);
          }else{
              courseEntity.setPurchased(false);
          }

          courseEntity.setNotesCount(getNotesCount(courseEntity).longValue());
          courseEntity.setVideoCount(getVideoCount(courseEntity));
          courseEntity.setRunningBatch(getRunningBatchesCount(courseEntity).intValue());
          courseEntity.setPractiseTestCount(getPractiseTestCount(courseEntity));

//          ArrayList<RunningBatchesModel> runningBatchList = runningBatchRepository.getBatchesList(
//                  courseModelArrayList.get(i).getInst_id(),courseModelArrayList.get(i).getId());
//
//          courseEntity.setBatchList(runningBatchList);


          courses.add(courseEntity);




      }

//        ArrayList<NotesModel>notesList = notesRepository.getNotesList(request.getInst_id());
//        if(!notesList.isEmpty()){
//            int videoLectureCount =  videoLectureRepository.getVideoLectureCount(request.getInst_id());
//            getCoursesResponse.setNotesCount(notesList.size());
//            getCoursesResponse.setVideoLectureCount(videoLectureCount);
//
//        }



        getCoursesResponse.setCourses(courses);
        getCoursesResponse.setMessage("Get Course Success");
        getCoursesResponse.setStatus(true);
        getCoursesResponse.seterrorCode(0);

        System.out.println(new Gson().toJson(getCoursesResponse));
        return getCoursesResponse;

    }


   Integer getNotesCount(CourseEntity entity){


        Integer instId = Math.toIntExact(entity.getInst_id());
      Long count= notesRepository.count("course_id=?1 and inst_id=?2 ",String.valueOf(entity.getId()),
             instId );
      return count.intValue();
    }

    Long getVideoCount(CourseEntity entity){
        Integer courseId = Math.toIntExact(entity.getId());
        Integer instId = Math.toIntExact(entity.getInst_id());

        Long count = videoLectureRepository.count
                ("course_id=?1 and inst_id=?2",courseId,instId);
        return  count;
    }

    Long getRunningBatchesCount(CourseEntity entity){
        Integer courseId = Math.toIntExact(entity.getId());
        Integer instId = Math.toIntExact(entity.getInst_id());

        Long count = runningBatchRepository.count("courseId =?1 and inst_id=?2",
                courseId,instId);
        return count;
    }

    Long getPractiseTestCount(CourseEntity entity){
        Integer courseId = Math.toIntExact(entity.getId());
        Integer instId = Math.toIntExact(entity.getInst_id());

        Long count = quizRepository.count("course_id =?1 and inst_id=?2",
                courseId,instId);
        return count;
    }


}
