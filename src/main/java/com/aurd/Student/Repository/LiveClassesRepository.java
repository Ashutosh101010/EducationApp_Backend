package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.LiveClassModel;
import com.aurd.Student.Model.Request.LiveClassesRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.Table;
import java.util.ArrayList;

@ApplicationScoped
public class LiveClassesRepository implements PanacheRepository<LiveClassModel> {


    public ArrayList getLiveSessions(LiveClassesRequest request){

        try{
            ArrayList<LiveClassModel> arrayList= (ArrayList<LiveClassModel>)
                    list("inst_id=?1 and status =?2 ORDER BY date_time DESC",request.getInst_id(),1);


            System.out.println(arrayList.size());


            return  arrayList;
//            Query query =
//                    getEntityManager().createQuery("select LiveClassModel from LiveClassModel liveclassModel" +
//                            " where liveclassModel.inst_id =: instId and liveclassModel.status=:status");
//            query.setParameter("instId",request.getInst_id());
//            query.setParameter("status",1);
//
//          ArrayList<LiveClassModel> list = (ArrayList<LiveClassModel>) query.getResultList();
//            list.forEach(liveClassModel -> {
//                System.out.println(new Gson().toJson(liveClassModel));
//            });

//            return (ArrayList<LiveClassModel>) query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
        }



    }


}
