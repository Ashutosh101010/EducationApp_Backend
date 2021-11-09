package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Request.GetCurrentAffairRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ApplicationScoped
public class CurrentAffairRepository implements PanacheRepository<CurrentAffairModel> {

    public ArrayList getCurrentAffairList(GetCurrentAffairRequest request){
        ArrayList<CurrentAffairModel> arrayList = new ArrayList();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if(request.getDate().isEmpty()|| request.getDate().equals("")){

            String lastId;
            if (request.getLastId().equals("")) {
                lastId = formatter.format(calendar.getTime());
            } else {
                lastId = request.getLastId();
            }

            String caQuery = "SELECT * from `current_affairs` where  inst_id = ? and created_at <? ORDER BY `time` DESC";
            Query currentAffair = getEntityManager().createNativeQuery(caQuery,
                    CurrentAffairModel.class);
            currentAffair.setParameter(1,request.getInst_id());
            currentAffair.setParameter(2,lastId);
          //  currentAffair.setParameter(2, Timestamp.valueOf(lastId));

          arrayList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();
//            List<CurrentAffairModel> list = list("inst_id  ORDER BY created_at ",request.getInst_id());
//            arrayList.addAll(list);

        }else{
            if(request.getDate().equals(formatter.format(calendar.getTime()))){

                String lastId;
                if (request.getLastId().equals("")) {
                    lastId = formatter.format(calendar.getTime());
                } else {
                    lastId = request.getLastId();
                }

                String caQuery = "SELECT * from `current_affairs` where  inst_id = ? and created_at<? ORDER BY `time` DESC";
                Query currentAffair = getEntityManager().createNativeQuery(caQuery,
                        CurrentAffairModel.class);
                currentAffair.setParameter(1,request.getInst_id());
                currentAffair.setParameter(2,lastId);

                arrayList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();

            }else{
                String start = request.getDate()+" 00:00:00";
                String end =  request.getDate()+" 23:59:59";

                String caQuery = "SELECT * from `current_affairs` where created_at BETWEEN ? AND ? AND inst_id = ?;";
                Query currentAffair = getEntityManager().createNativeQuery(caQuery,
                        CurrentAffairModel.class);
                currentAffair.setParameter(1,start);
                currentAffair.setParameter(2,end);
                currentAffair.setParameter(3,request.getInst_id());
                arrayList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();

            }




        }

        return  arrayList;

    }

    public CurrentAffairModel getBookmarkCurrentAffair(long id){
        CurrentAffairModel currentAffairModel = find("id",id).firstResult();
        return  currentAffairModel;
    }





}
