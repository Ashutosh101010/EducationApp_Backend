package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.NotesModel;
import com.aurd.Student.Model.Entity.VideoModel;
import com.aurd.Student.Model.Request.GetFreeStudyMaterialRequest;
import com.aurd.Student.Model.Response.GetFreeStudyMaterialResponse;
import com.aurd.Student.Repository.*;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/getFreeStudyMaterial")
public class GetFreeStudyMaterial {


  @Inject
    VideoLectureRepository videoLectureRepository;

  @Inject
    NotesRepository notesRepository;



    @POST
    public GetFreeStudyMaterialResponse getFreeStudyMaterial(GetFreeStudyMaterialRequest request)
    {

        GetFreeStudyMaterialResponse response=new GetFreeStudyMaterialResponse();
        List<VideoModel> videoModels=videoLectureRepository.getFreeVideos(request);
        List<NotesModel> notesModels=notesRepository.getFreeNotes(request);


        response.setNotes(notesModels);
        response.setVideos(videoModels);
        response.setStatus(true);
        response.setErrorCode(0);
        return response;


    }
}
