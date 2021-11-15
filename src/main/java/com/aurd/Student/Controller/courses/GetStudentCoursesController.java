package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.BeanClass.CourseEntity;
import com.aurd.Student.Model.BeanClass.StudentCourseEntity;
import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.StudentCourseModel;
import com.aurd.Student.Model.Request.GetStudentCourseRequest;
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

@Path("/courses/getStudentCourse")
public class GetStudentCoursesController {

    @Inject
    CoursesRepository repository;

    @Inject
    StudentCourseRepository studentCourseRepository;

    @Inject
    NotesRepository notesRepository;

    @Inject
    VideoLectureRepository videoLectureRepository;


    @Inject
    RunningBatchRepository runningBatchRepository;


    @Inject
    QuizRepository quizRepository;



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional


    public GetStudentCourseResponse getStudentCourse(GetStudentCourseRequest request){


        ArrayList<StudentCourseEntity> mainList = new ArrayList<>();
        ArrayList<StudentCourseModel> arrayList = studentCourseRepository.getStudentCourses(request);

        System.out.println(arrayList.size());

        arrayList.forEach(studentCourseModel -> {
            Gson gson = new Gson();
            StudentCourseEntity studentCourseEntity = gson.fromJson(new Gson().toJson(studentCourseModel),
                    StudentCourseEntity.class);

            System.out.println(studentCourseModel.getCourseId());

            CourseModel courseModel = repository.getCourseDetails
                    (studentCourseModel.getInst_id(),studentCourseModel.getCourseId());

            studentCourseEntity.setCourse(courseModel.getCourse());
            studentCourseEntity.setDescription(courseModel.getDescription());
            studentCourseEntity.setLogo(courseModel.getLogo());
            studentCourseEntity.setDuration(courseModel.getDuration());
            studentCourseEntity.setFee(courseModel.getFee());
            studentCourseEntity.setBanner_img(courseModel.getBanner_img());
            studentCourseEntity.setLanguage(courseModel.getLanguage());

            studentCourseEntity.setNotesCount(getNotesCount(courseModel).longValue());
            studentCourseEntity.setVideoCount(getVideoCount(courseModel));
            studentCourseEntity.setRunningBatch(getRunningBatchesCount(courseModel));

            studentCourseEntity.setPractiseTestCount(getPractiseTestCount(courseModel));


            mainList.add(studentCourseEntity);

        });

        GetStudentCourseResponse response = new GetStudentCourseResponse();
        response.setCourseList(mainList);
        response.setStatus(true);
        response.setErrorCode(0);
        response.setMessage("Get Student Course Successfully");
        return  response;
    }

    Integer getNotesCount(CourseModel entity){


        Integer instId = Math.toIntExact(entity.getInst_id());
        Long count= notesRepository.count("course_id=?1 and inst_id=?2 ",String.valueOf(entity.getId()),
                instId );
        return count.intValue();
    }

    Long getVideoCount(CourseModel entity){
        Integer courseId = Math.toIntExact(entity.getId());
        Integer instId = Math.toIntExact(entity.getInst_id());

        Long count = videoLectureRepository.count
                ("course_id=?1 and inst_id=?2",courseId,instId);
        return  count;
    }

    Long getRunningBatchesCount(CourseModel entity){
        Integer courseId = Math.toIntExact(entity.getId());
        Integer instId = Math.toIntExact(entity.getInst_id());

        Long count = runningBatchRepository.count("courseId =?1 and inst_id=?2",
                courseId,instId);
        return count;
    }

    Long getPractiseTestCount(CourseModel entity){
        Integer courseId = Math.toIntExact(entity.getId());
        Integer instId = Math.toIntExact(entity.getInst_id());

        Long count = quizRepository.count("course_id =?1 and inst_id=?2",
                courseId,instId);
        return count;
    }


}
