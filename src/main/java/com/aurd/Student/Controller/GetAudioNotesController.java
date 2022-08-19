package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.Audio;
import com.aurd.Student.Model.Request.GetAudioNotesRequest;
import com.aurd.Student.Model.Request.GetVideoLectureRequest;
import com.aurd.Student.Model.Response.GetAudioNotesResponse;
import com.aurd.Student.Repository.AudioNotesRepository;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getAudioNotes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetAudioNotesController {

    @Inject
    AudioNotesRepository audioNotesRepository;

    @POST

    public GetAudioNotesResponse getVideos(GetAudioNotesRequest request) {

        ArrayList<Audio> arrayList = audioNotesRepository.getCourseAudioList(request);
        GetAudioNotesResponse response = new GetAudioNotesResponse();
        response.setErrorCode(0);
        response.setMessage("Get audios success");
        response.setStatus(true);
        response.setAudioList(arrayList);


        return response;
    }
}

