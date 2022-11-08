package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.BeanClass.CourseEntity;
import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.StudentCourseModel;
import com.aurd.Student.Model.Request.GetCoursesRequest;
import com.aurd.Student.Model.Response.GetCourseDetailResponse;
import com.aurd.Student.Repository.*;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/courses/getCourseDetails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetCourseDetailController {

    @Inject
    CoursesRepository coursesRepository;

    @Inject
    StudentCourseRepository studentCourseRepository;

    @Inject
    RunningBatchRepository runningBatchRepository;

    @Inject
    NotesRepository notesRepository;

    @Inject
    VideoLectureRepository videoLectureRepository;

    @Inject
    QuizRepository quizRepository;

    @POST
    @Transactional

    public GetCourseDetailResponse getDetail(GetCoursesRequest request){


        CourseModel courseModel = coursesRepository.find("id =?1 and inst_id =?2",
               request.getCourse_id(),request.getInst_id()).
                firstResult();
        System.out.println(new Gson().toJson(courseModel));

        CourseEntity courseEntity =
                new Gson().fromJson(new Gson().toJson(courseModel),CourseEntity.class);

        System.out.println(new Gson().toJson(courseEntity));


        StudentCourseModel scm = studentCourseRepository.find("courseId=?1 and userId=?2",
                ((int) courseEntity.getId()),request.getStud_id()).firstResult();

        if(scm!=null){
            courseEntity.setPurchased(true);
        }else{
            courseEntity.setPurchased(false);
        }

        courseEntity.setNotesCount(getNotesCount(courseEntity).longValue());
        courseEntity.setVideoCount(getVideoCount(courseEntity));
        courseEntity.setRunningBatch(getRunningBatchesCount(courseEntity).intValue());
        courseEntity.setPractiseTestCount(getPractiseTestCount(courseEntity));



        GetCourseDetailResponse   response = new  GetCourseDetailResponse();
        response.setErrorCode(0);
        response.setStatus(true);
        response.setEntity(courseEntity);
        response.setMessage("Get Course success");
        return response;
    }



    Integer getNotesCount(CourseEntity entity){


        Integer instId = Math.toIntExact(entity.getInst_id());
        Long count= notesRepository.count("course_id=?1 and inst_id=?2 ",entity.getId(),
                instId );
        return count.intValue();
    }

    Long getVideoCount(CourseEntity entity){

        Integer instId = Math.toIntExact(entity.getInst_id());

        Long count = videoLectureRepository.count
                ("course_id=?1 and inst_id=?2",entity.getId(),instId);
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

        Long count = quizRepository.count("course_id =?1 and inst_id=?2 and type = ?3",
                courseId,instId,"Monthly Test");
        return count;
    }



}
