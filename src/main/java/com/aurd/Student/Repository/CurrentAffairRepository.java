package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Request.GetCurrentAffairRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CurrentAffairRepository implements PanacheRepository<CurrentAffairModel> {

    public ArrayList getCurrentAffairList(GetCurrentAffairRequest request){

        if(request.getDate().isEmpty()|| request.getDate().equals("")){
            ArrayList<CurrentAffairModel> arrayList = new ArrayList();
            List<CurrentAffairModel> list = list("inst_id",request.getInst_id());
            arrayList.addAll(list);
            return  arrayList;
        }else{
            String start = request.getDate()+" 00:00:00";
            String end =  request.getDate()+" 23:59:59";

            String caQuery = "SELECT * from `current_affairs` where created_at BETWEEN ? AND ? AND inst_id = ? ;";
            Query currentAffair = getEntityManager().createNativeQuery(caQuery,
                    CurrentAffairModel.class);
            currentAffair.setParameter(1,start);
            currentAffair.setParameter(2,end);
            currentAffair.setParameter(3,request.getInst_id());
            ArrayList<CurrentAffairModel> caList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();

            return  caList;

        }


    }

    public CurrentAffairModel getBookmarkCurrentAffair(long id){
        CurrentAffairModel currentAffairModel = find("id",id).firstResult();
        return  currentAffairModel;
    }





}
