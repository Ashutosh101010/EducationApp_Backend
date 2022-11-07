package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.LiveClass;
import com.aurd.Student.Model.Request.LiveClassesRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.Table;
import java.util.ArrayList;

@ApplicationScoped
public class LiveClassesRepository implements PanacheRepository<LiveClass> {


//    public ArrayList getLiveSessions(LiveClassesRequest request){
//
//        try{
//            ArrayList<LiveClassModel> arrayList= (ArrayList<LiveClassModel>)
//                    list("inst_id=?1 and status =?2 or inst_id=?1 and status=?3 ORDER BY date_time DESC",request.getInst_id(),1,0);
//            System.out.println(arrayList.size());
//
//            return  arrayList;
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ArrayList();
//        }
//
//
//
//    }


}
