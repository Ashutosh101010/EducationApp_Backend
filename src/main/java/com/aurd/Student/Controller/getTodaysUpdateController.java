package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.TodaysUpdateEntity;
import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.VideoModel;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.CoursesRepository;
import com.aurd.Student.Repository.QuizRepository;
import com.aurd.Student.Repository.VideoLectureRepository;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getTodaysUpdate")
public class getTodaysUpdateController {

    @Inject
    CoursesRepository coursesRepository;

    @Inject
    VideoLectureRepository videoLectureRepository;

    @Inject
    QuizRepository quizRepository;


    @GET
    @Produces({MediaType.APPLICATION_JSON})


    @Transactional
    public TodaysUpdateEntity getTodayUpdate(@QueryParam ("instID") long instID){

        TodaysUpdateEntity updateEntity = new TodaysUpdateEntity();

        coursesRepository.getTodayUpdateData((int) instID);
//        ArrayList<CourseModel> courseModelList = coursesRepository.getCourses( (int) instID);
//        ArrayList<VideoModel> videoLecturesList = videoLectureRepository.getVideoLectureList(instID);
//        ArrayList<QuizModel> quizzesList =   quizRepository.getAllUpdates(instID);



        updateEntity.setMessage("get today's update success");
        updateEntity.setStatus(true);
        updateEntity.setErrorCode(0);
//        updateEntity.setQuizzes(quizzesList);
//        updateEntity.setVideos(videoLecturesList);
//        updateEntity.setCourses(courseModelList);


        return  updateEntity;


    }
}
