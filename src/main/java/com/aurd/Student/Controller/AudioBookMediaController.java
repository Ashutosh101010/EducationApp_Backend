package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.AudioBook;

import com.aurd.Student.Model.Entity.AudioBookMedia;
import com.aurd.Student.Model.Request.AudioBookMediaRequest;
import com.aurd.Student.Model.Response.AudioBookMediaResponse;
import com.aurd.Student.Repository.AudioBookMediaRepository;


import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/audioBookMedia")
public class AudioBookMediaController {


    @Inject
    AudioBookMediaRepository audioBookMediaRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AudioBookMediaResponse audioBookMediaResponse(AudioBookMediaRequest request)
    {
        AudioBookMediaResponse response=new AudioBookMediaResponse();


        Query query=audioBookMediaRepository.getEntityManager().createQuery("select AudioBookMedia from AudioBookMedia AudioBookMedia where AudioBookMedia.audio_book_id=:audioBookId order by AudioBookMedia.s_no asc ");
        query.setParameter("audioBookId",request.getAudioBookId());
        List<AudioBookMedia> audioBooks=query.getResultList();

        response.setErrorCode(0);
        response.setStatus(true);
        response.setAudioBookMediaList(audioBooks);
        response.setMessage("success");
        return response;
    }
}
