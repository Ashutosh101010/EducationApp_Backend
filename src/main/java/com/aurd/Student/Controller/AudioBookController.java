package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.AudioBook;
import com.aurd.Student.Model.Entity.PurchaseAudioBook;
import com.aurd.Student.Model.Request.AudioBookRequest;
import com.aurd.Student.Model.Response.AudioBookResponse;
import com.aurd.Student.Repository.AudioBookRepository;
import com.aurd.Student.Repository.PurchaseAudioBookRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/audioBook")
public class AudioBookController {


    @Inject
    AudioBookRepository audioBookRepository;

    @Inject
    PurchaseAudioBookRepository repository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AudioBookResponse audioBookResponse(AudioBookRequest request)
    {

        AudioBookResponse response=new AudioBookResponse();

       List<AudioBook> audioBooks=audioBookRepository.find("inst_id",request.getInst_id()).list();
       audioBooks.forEach(audioBook -> {
PurchaseAudioBook purchaseAudioBook=repository.find("audiobooksid=?1 and stud_id=?2",audioBook.getId(),request.getStud_id()).firstResult();
      if(purchaseAudioBook==null)
      {
          audioBook.setPurchased(false);
      }
      else{
          audioBook.setPurchased(true);
      }
       });

        response.setErrorCode(0);
        response.setStatus(true);
        response.setAudioBookList(audioBooks);
        response.setMessage("success");
        return response;
    }
}
