package com.aurd.Student.Repository.comment;

import com.aurd.Student.Model.Entity.Blog_Comment_Model;
import com.aurd.Student.Model.Entity.Current_AffairsCommented_Model;

import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.aurd.Student.Model.Request.GetCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@ApplicationScoped
public class Current_Affair_Comment_Repository implements PanacheRepository<Current_AffairsCommented_Model> {
    public ArrayList<Current_AffairsCommented_Model> getComment(GetCommentRequest request) {

       //     Query query = getEntityManager().createQuery("select Current_AffairsCommented_Model from Current_AffairsCommented_Model current join StudentModel student on current.added_by=student.id where current.current_affair_id=:postId");
       //     query.setParameter("postId", request.getPost_id());

//        try{
//            ArrayList<Current_AffairsCommented_Model> arrayList = (ArrayList<Current_AffairsCommented_Model>)
//                    find("current_affair_id =?1" ,
//                    request.getPost_id()).list();
//            return  arrayList;
//        }catch (Exception e){
//            System.out.println(e);
        Query query=getEntityManager().createQuery("select Current_AffairsCommented_Model from Current_AffairsCommented_Model current" +
                " left outer join TeacherModel Teacher on current.added_by=Teacher.id" +
                " left  outer join StudentModel Students on Students.id=current.added_by where current.current_affair_id=:postId");


        return (ArrayList<Current_AffairsCommented_Model>) query.getResultList();

        }


    public boolean addCurrentAffairCommentRequest(AddPostCommentRequest request) {
        Current_AffairsCommented_Model current_affairsCommented_model =
                new Gson().fromJson(new Gson().toJson(request),Current_AffairsCommented_Model.class);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        current_affairsCommented_model.setCurrent_affair_id(request.getPost_id());
        current_affairsCommented_model.setAdded_on(Timestamp.
                valueOf(simpleDateFormat.format(calendar.getTime())));

        current_affairsCommented_model.setType("student");
        persist(current_affairsCommented_model);

        current_affairsCommented_model.setType("student");
        return true;

          }
       }
