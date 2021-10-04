package com.aurd.Student.Repository.comment;

import com.aurd.Student.Model.Entity.Current_AffairsCommented_Model;

import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.aurd.Student.Model.Request.GetCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@ApplicationScoped
public class Current_Affair_Comment_Repository implements PanacheRepository<Current_AffairsCommented_Model> {

    public ArrayList getComment(GetCommentRequest request){

        try{
            ArrayList<Current_AffairsCommented_Model> arrayList = (ArrayList<Current_AffairsCommented_Model>)
                    find("current_affair_id =?1" ,
                    request.getPost_id()).list();
            return  arrayList;
        }catch (Exception e){
            System.out.println(e);
        }


        return new ArrayList();

      }

    public boolean addCurrentAffairCommentRequest(AddPostCommentRequest request) {
        Current_AffairsCommented_Model current_affairsCommented_model =
                new Gson().fromJson(new Gson().toJson(request),Current_AffairsCommented_Model.class);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        current_affairsCommented_model.setCurrent_affair_id(request.getPost_id());
        current_affairsCommented_model.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        persist(current_affairsCommented_model);
        return true;

          }
       }
