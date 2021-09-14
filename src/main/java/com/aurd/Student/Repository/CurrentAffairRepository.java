package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Request.GetCurrentAffairRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CurrentAffairRepository implements PanacheRepository<CurrentAffairModel> {

    public ArrayList getCurrentAffairList(GetCurrentAffairRequest request){
        ArrayList<CurrentAffairModel> arrayList = new ArrayList();
        List<CurrentAffairModel> list = list("inst_id",request.getInst_id());
        arrayList.addAll(list);
        return  arrayList;
    }





}
