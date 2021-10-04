package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.BlogEntity;
import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Entity.Index_Model;
import com.aurd.Student.Model.Entity.NotesModel;
import com.aurd.Student.Model.Request.GetIndexRequest;
import com.aurd.Student.Model.Response.GetIndexResponse;
import com.aurd.Student.Repository.*;
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

    @Inject
    NotesRepository notesRepository;

    @Inject
    NotesLikeDislikeRepository notesLikeDislikeRepository;

    @Inject
    TeacherRepository teacherRepository;

    @Inject
    NotesCommentRepository notesComentRepository;


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
             //    Long value = Long.valueOf(model.getPost_id());

                 String blogQuery = "SELECT * from `blog` where inst_id = ? and id=?";
                 Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery);
                 blog.setParameter(1, request.getInst_id());
                 blog.setParameter(2,model.getPost_id());
                 ArrayList <Object[]>blogList = (ArrayList<Object[]>) blog.getResultList();

                 System.out.println(blogList.size());

                 blogList.forEach(objects1 -> {
                     BlogEntity blogModel = new BlogEntity();
                     blogModel.setId(Long.parseLong(objects1[0].toString()));
                     blogModel.setTitle(objects1[1].toString());
                     blogModel.setDescription(objects1[2].toString());
                     blogModel.setAdded_by(Integer.parseInt(objects1[3].toString()));
                     blogModel.setCreated_on(Timestamp.valueOf(objects1[4].toString()));
                     blogModel.setInst_id(Long.parseLong(objects1[6].toString()));
                     blogModel.setTags(objects1[7].toString());
                     blogModel.setThumbnail(objects1[8].toString());
                     blogModel.setType("blog");

                     vList.add(blogModel);

                 });


             } else if(model.getType().equals("current_affair")){

                 String caQuery = "SELECT * from `current_affairs` where  inst_id = ?  and id=?";
                 Query currentAffair = caRepository.getEntityManager().createNativeQuery(caQuery);
                 currentAffair.setParameter(1, request.getInst_id());
                 currentAffair.setParameter(2,model.getPost_id());

                 ArrayList<Object[]> caList =(ArrayList<Object[]>) currentAffair.getResultList();

                 caList.forEach(objects1 -> {
                     CurrentAffairEntity caModal = new CurrentAffairEntity();
                     caModal.setId(Long.parseLong(objects1[0].toString()));
                     caModal.setTitle(objects1[1].toString());
                     caModal.setDescription(objects1[2].toString());
                     caModal.setCreated_at(Timestamp.valueOf(objects1[3].toString()));
                     caModal.setAdded_by(Integer.parseInt(objects1[5].toString()));
                     caModal.setInst_id(Integer.parseInt(objects1[6].toString()));
                     caModal.setThumbnail(objects1[7].toString());
                     caModal.setType("currentAffair");

                     vList.add(caModal);

                 });

             }else if(model.getType().equals("notes")){

                 String notesQuery = "SELECT * from `notes` where inst_id = ? and id=?";
                 Query notes = notesRepository.getEntityManager().createNativeQuery(notesQuery);
                 notes.setParameter(1,request.getInst_id());
                 notes.setParameter(2,model.getPost_id());

                 ArrayList<Object[]> notesList = (ArrayList<Object[]>) notes.getResultList();

                 notesList.forEach(objects -> {
                     NotesEntity notesEntity =new NotesEntity();
                     notesEntity.setId(Long.parseLong(objects[0].toString()));
                     notesEntity.setName(objects[1].toString());
                     notesEntity.setFile(objects[2].toString());
                     notesEntity.setTopicId(Integer.parseInt(objects[3].toString()));
                     notesEntity.setCreated_at(Timestamp.valueOf(objects[4].toString()));
                     notesEntity.setInst_id(Integer.parseInt(objects[6].toString()));
                     notesEntity.setCreated_by(Integer.parseInt(objects[7].toString()));
                     notesEntity.setTopic(objects[8].toString());
                     notesEntity.setComment(Long.parseLong(objects[9].toString()));
                     notesEntity.setFee_type(objects[12].toString());
                  //   notesEntity.setLike(Long.parseLong(objects[11].toString()));
                  //   notesEntity.setDescription(objects[10].toString());
                  //   notesEntity.setSubject(objects[15].toString());
                     notesEntity.setType("notes");


                     String likeQuery = "SELECT * FROM `notes_liked` WHERE notes_id =? ";
                     Query like = notesLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
                     like.setParameter(1, notesEntity.getId());
                     ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
                     likeList.forEach(likeObject -> {
                         if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
                             System.out.println("Liked");
                             notesEntity.setLiked(true);
                         }
                     });

                     Integer likeCount = likeList.size();
                     notesEntity.setLike(likeCount.longValue());
                     vList.add(notesEntity);


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
