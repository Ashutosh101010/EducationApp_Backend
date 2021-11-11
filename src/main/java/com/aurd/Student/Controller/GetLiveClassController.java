package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.LiveClassModel;
import com.aurd.Student.Model.Entity.TeacherModel;
import com.aurd.Student.Model.Request.LiveClassesRequest;
import com.aurd.Student.Model.Response.GetLiveClassResponse;
import com.aurd.Student.Repository.CoursesRepository;
import com.aurd.Student.Repository.LiveClassesRepository;
import com.aurd.Student.Repository.TeacherRepository;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getLiveClasses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetLiveClassController {

    @Inject
    LiveClassesRepository repository;

    @Inject
    CoursesRepository coursesRepository;

    @Inject
    TeacherRepository teacherRepository;

    @POST
    @Transactional

    public GetLiveClassResponse getLiveClasses(LiveClassesRequest request){

       ArrayList<LiveClassModel> arrayList = repository.getLiveSessions(request);

       arrayList.forEach(liveClassModel -> {
           Integer course = liveClassModel.getCourse();
           Integer faculty = liveClassModel.getFaculty_id();
         CourseModel courseModel = coursesRepository.find("id",course.longValue()).firstResult();
         if(courseModel!=null){
             liveClassModel.setCourseName(courseModel.getCourse());

         }

       TeacherModel model = teacherRepository.find("id",faculty.longValue()).firstResult();
         if(model!=null){
             liveClassModel.setTeacherName(model.getFname());
             liveClassModel.setImage(model.getProfile());
         }
       });


        GetLiveClassResponse response = new GetLiveClassResponse();
        response.setList(arrayList);
        response.setStatus(true);
        response.setErrorCode(0);
        response.setMessage("Live Class found successfully");

        return response;
    }


}
