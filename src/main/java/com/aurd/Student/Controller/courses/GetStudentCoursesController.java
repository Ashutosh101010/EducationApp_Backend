package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.BeanClass.StudentCourseEntity;
import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.StudentCourseModel;
import com.aurd.Student.Model.Request.GetStudentCourseRequest;
import com.aurd.Student.Model.Response.GetStudentCourseResponse;
import com.aurd.Student.Repository.CoursesRepository;
import com.aurd.Student.Repository.StudentCourseRepository;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional


    public GetStudentCourseResponse getStudentCourse(GetStudentCourseRequest request){


        ArrayList<StudentCourseEntity> mainList = new ArrayList<>();
        ArrayList<StudentCourseModel> arrayList = studentCourseRepository.getStudentCourses(request);
        arrayList.forEach(studentCourseModel -> {
            Gson gson = new Gson();
            StudentCourseEntity studentCourseEntity = gson.fromJson(new Gson().toJson(studentCourseModel),
                    StudentCourseEntity.class);

            CourseModel courseModel = repository.getCourseDetails
                    (studentCourseModel.getInst_id(),studentCourseModel.getCourseId());



            studentCourseEntity.setCourse(courseModel.getCourse());
            studentCourseEntity.setDescription(courseModel.getDescription());
            studentCourseEntity.setLogo(courseModel.getLogo());
            studentCourseEntity.setDuration(courseModel.getDuration());
            studentCourseEntity.setFee(courseModel.getFee());
            studentCourseEntity.setBanner_img(courseModel.getBanner_img());
            studentCourseEntity.setLanguage(courseModel.getLanguage());

            mainList.add(studentCourseEntity);

        });

        GetStudentCourseResponse response = new GetStudentCourseResponse();
        response.setCourseList(mainList);
        response.setStatus(true);
        response.setErrorCode(0);
        response.setMessage("Get Student Course Successfully");
        return  response;
    }
}
