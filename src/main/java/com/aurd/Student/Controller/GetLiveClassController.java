package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.LiveClassModel;
import com.aurd.Student.Model.Entity.StudentCourseModel;
import com.aurd.Student.Model.Entity.TeacherModel;
import com.aurd.Student.Model.Request.LiveClassesRequest;
import com.aurd.Student.Model.Response.GetLiveClassResponse;
import com.aurd.Student.Repository.CoursesRepository;
import com.aurd.Student.Repository.LiveClassesRepository;
import com.aurd.Student.Repository.StudentCourseRepository;
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
import java.util.List;

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

    @Inject
    StudentCourseRepository studentCourseRepository;

    @POST

    public GetLiveClassResponse getLiveClasses(LiveClassesRequest request){

       ArrayList<LiveClassModel> arrayList = repository.getLiveSessions(request);

       arrayList.forEach(liveClassModel -> {
           Integer faculty = liveClassModel.getFaculty_id();


          List<StudentCourseModel> studentCourseModels= studentCourseRepository.getPurchasedCourse(request.getStud_id(),liveClassModel.getCourses());

          System.out.println(studentCourseModels);
          if(studentCourseModels==null || studentCourseModels.isEmpty())
          {
              liveClassModel.setPurchased(false);
          }else{
              liveClassModel.setPurchased(true);
          }
//         CourseModel courseModel = coursesRepository.find("id",course.longValue()).firstResult();
//         if(courseModel!=null){
//             liveClassModel.setCourseName(courseModel.getCourse());
//             StudentCourseModel scm = studentCourseRepository.find("courseId=?1 and userId=?2",
//                     course.longValue(),request.getStud_id()).firstResult();
//             if(scm!=null){
//                 liveClassModel.setPurchased(true);
//             }else{
//                 liveClassModel.setPurchased(false);
//             }
//
//
//         }

       TeacherModel model = teacherRepository.find("id",faculty.longValue()).firstResult();
         if(model!=null){
             liveClassModel.setTeacherName(model.getFname());
             liveClassModel.setImage(model.getProfile());
         }
       });

       System.out.println(arrayList);


        GetLiveClassResponse response = new GetLiveClassResponse();
        response.setList(arrayList);
        response.setStatus(true);
        response.setErrorCode(0);
        response.setMessage("Live Class found successfully");

        return response;
    }


}
