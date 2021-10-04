package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Index_Model;
import com.aurd.Student.Model.Request.GetIndexRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped

public class IndexRepository implements PanacheRepository<Index_Model> {


    public ArrayList getIndex(GetIndexRequest request){

        ArrayList<Index_Model> arrayList = (ArrayList<Index_Model>) list("inst_id =?1 ", request.getInst_id());
        return arrayList;

    }
}
