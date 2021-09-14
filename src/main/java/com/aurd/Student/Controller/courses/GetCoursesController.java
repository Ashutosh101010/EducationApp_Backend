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


    @POST
    @Transactional
    public GetCoursesResponse getCourses(GetCoursesRequest request){
        GetCoursesResponse getCoursesResponse = new GetCoursesResponse();
      ArrayList<CourseModel> courseModelArrayList = repository.getCourses(request.getInst_id());
      ArrayList<CourseEntity> courses = new ArrayList<>();

        for(int i=0;i<courseModelArrayList.size();i++){
            CourseEntity courseEntity  = new Gson().fromJson
                  (new Gson().toJson(courseModelArrayList.get(i)),CourseEntity.class);

          ArrayList<RunningBatchesModel> runningBatchList = runningBatchRepository.getBatchesList(
                  courseModelArrayList.get(i).getInst_id(),courseModelArrayList.get(i).getId());

          courseEntity.setBatchList(runningBatchList);

          courses.add(courseEntity);
      }

        ArrayList<NotesModel>notesList = notesRepository.getNotesList(request.getInst_id());
        if(!notesList.isEmpty()){
            int videoLectureCount =  videoLectureRepository.getVideoLectureCount(request.getInst_id());
            getCoursesResponse.setNotesCount(notesList.size());
            getCoursesResponse.setVideoLectureCount(videoLectureCount);

        }



        getCoursesResponse.setCourses(courses);
        getCoursesResponse.setMessage("Get Course Success");
        getCoursesResponse.setStatus(true);
        getCoursesResponse.setStatusCode(0);


        return getCoursesResponse;

    }



}
