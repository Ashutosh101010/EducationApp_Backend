package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.LiveClassModel;
import com.aurd.Student.Model.Request.LiveClassesRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.Table;
import java.util.ArrayList;

@ApplicationScoped
public class LiveClassesRepository implements PanacheRepository<LiveClassModel> {


    public ArrayList getLiveSessions(LiveClassesRequest request){

        try{
            Query query =
                    getEntityManager().createQuery("select LiveClassModel from LiveClassModel liveclassModel" +
                            " where LiveClassModel .inst_id =: instId and LiveClassModel.status=:status");
            query.setParameter("instId",request.getInst_id());
            query.setParameter("status",0);

            return (ArrayList<LiveClassModel>) query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
        }



    }


}
