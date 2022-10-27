package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.VideoModel;
import com.aurd.Student.Model.Request.GetVideoLectureRequest;
import com.aurd.Student.Model.Response.GetVideoLectureResponse;
import com.aurd.Student.Repository.VideoLectureRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getVideoLectures")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetVideoLectureController {

    @Inject
    VideoLectureRepository videoLectureRepository;
    @POST

    public GetVideoLectureResponse getVideos(GetVideoLectureRequest request){

       ArrayList<VideoModel> arrayList= videoLectureRepository.getVideoLectureList(request.getInstID());
       GetVideoLectureResponse response = new GetVideoLectureResponse();
       response.setErrorCode(0);
       response.setMessage("Get videos success");
       response.setStatus(true);
       response.setVideoList(arrayList);


       return  response;
    }



}
