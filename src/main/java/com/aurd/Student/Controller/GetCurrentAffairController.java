package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.Entity.BookMarkModel;
import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Request.GetCurrentAffairRequest;
import com.aurd.Student.Model.Response.GetCurrentAffairResponse;
import com.aurd.Student.Repository.BookMarkRepository;
import com.aurd.Student.Repository.CurrentAffairRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("/getCurrentAffair")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetCurrentAffairController {

    @Inject
    CurrentAffairRepository repository;


    @Inject
    BookMarkRepository bookMarkRepository;

    @POST

    @Transactional
    public GetCurrentAffairResponse getList(GetCurrentAffairRequest request){


        ArrayList<CurrentAffairModel> list = repository.getCurrentAffairList(request);

        GetCurrentAffairResponse getCurrentAffairResponse = new GetCurrentAffairResponse();



        if(list.isEmpty()){

            getCurrentAffairResponse.setMessage("No Current Affairs");
            getCurrentAffairResponse.setStatus(false);
            getCurrentAffairResponse.setStatusCode(1);

        }else{


            ArrayList<CurrentAffairEntity> aList = new ArrayList<>();
            list.forEach(model -> {
              CurrentAffairEntity currentAffairEntity =
                        new Gson().fromJson(new Gson().toJson(model),CurrentAffairEntity.class);

                System.out.println(new Gson().toJson(currentAffairEntity));


                ArrayList<BookMarkModel> arrayList = (ArrayList<BookMarkModel>) bookMarkRepository.list("type=?1 and post_id=?2",
                        "currentAffair",model.getId());
                arrayList.forEach(bookMarkModel -> {

                    if(bookMarkModel.getAdded_by()==request.getStud_id()){
                        currentAffairEntity.setAdded(true);
                    }else{
                        currentAffairEntity.setAdded(false);
                    }
                });

                aList.add(currentAffairEntity);

            });

            getCurrentAffairResponse.setCurrentAffair(aList);
            getCurrentAffairResponse.setMessage("Get Current Affair Success");
            getCurrentAffairResponse.setStatus(true);
            getCurrentAffairResponse.setStatusCode(0);
        }


        return  getCurrentAffairResponse;

    }


}
