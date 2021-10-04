package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Entity.Index_Model;
import com.aurd.Student.Model.Request.GetIndexRequest;
import com.aurd.Student.Model.Response.GetIndexResponse;
import com.aurd.Student.Repository.BlogRepository;
import com.aurd.Student.Repository.CurrentAffairRepository;
import com.aurd.Student.Repository.IndexRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;

@Path("/getIndex")

public class GetIndexController {

    @Inject
    IndexRepository indexRepository;

    @Inject
    BlogRepository blogRepository;

    @Inject
    CurrentAffairRepository caRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    @Transactional
    public GetIndexResponse getIndex(GetIndexRequest request){


        ArrayList<Index_Model> arrayList = new ArrayList<>();
     //   ArrayList<BlogModel> blogList = new ArrayList<>();

        ArrayList<Object> vList = new ArrayList<>();

        String string="SELECT * FROM `index_data` WHERE inst_id=?  ORDER By created_on DESC";
        Query query = indexRepository.getEntityManager().createNativeQuery(string);
        query.setParameter(1,request.getInst_id());


        ArrayList<Object[]> list = (ArrayList<Object[]>) query.getResultList();



         list.forEach(objects -> {
                     Index_Model model = new Index_Model();
                     model.setId(Integer.parseInt(objects[0].toString()));
                     model.setPost_id(Integer.parseInt(objects[1].toString()));
                     model.setCreated_on(Timestamp.valueOf(objects[2].toString()));
                     model.setType(objects[3].toString());
                     arrayList.add(model);

         });

         System.out.println(arrayList.size());

         arrayList.forEach(model -> {

             if(model.getType().equals("blog")){
                 String blogQuery = "SELECT * from `blog` where inst_id = ? ORDER BY created_on DESC";
                 Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
                 blog.setParameter(1, request.getInst_id());
                 ArrayList <Object[]>blogList = (ArrayList<Object[]>) blog.getResultList();


                 blogList.forEach(objects1 -> {
                     BlogModel blogModel = new BlogModel();
                     blogModel.setId(Long.parseLong(objects1[0].toString()));
                     blogModel.setThumbnail(objects1[1].toString());
                     blogModel.setInst_id(Long.parseLong(objects1[2].toString()));
                     blogModel.setDescription(objects1[3].toString());
                     blogModel.setAdded_by(Integer.parseInt(objects1[4].toString()));
                     blogModel.setTitle(objects1[5].toString());

                     vList.add(blogModel);

                 });

             } else if(model.getType().equals("current_affair")){

                 String caQuery = "SELECT * from `current_affairs` where  inst_id = ? ORDER BY created_at DESC;";
                 Query currentAffair = caRepository.getEntityManager().createNativeQuery(caQuery);
                 currentAffair.setParameter(1, request.getInst_id());

                 ArrayList<Object[]> caList =(ArrayList<Object[]>) currentAffair.getResultList();

                 caList.forEach(objects1 -> {
                     CurrentAffairModel caModal = new CurrentAffairModel();
                     caModal.setId(Long.parseLong(objects1[0].toString()));
                     caModal.setDescription(objects1[1].toString());
                     caModal.setThumbnail(objects1[2].toString());
                     caModal.setTitle(objects1[3].toString());
                     caModal.setAdded_by(Integer.parseInt(objects1[4].toString()));
                     caModal.setInst_id(Integer.parseInt(objects1[5].toString()));
                     vList.add(caModal);

                 });

             }
         });

        GetIndexResponse getIndexResponse= new GetIndexResponse();
        getIndexResponse.setIndex(arrayList);
        getIndexResponse.setMessage("Get Index Successfully");
        getIndexResponse.setStatus(true);
        getIndexResponse.setErrorCode(0);
        getIndexResponse.setObjList(vList);


        return getIndexResponse;
    }
}
