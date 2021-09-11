package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Institute_Master_Model;
import com.aurd.Student.Model.Request.GetInstituteDetailsRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class GetInstituteDetailsRepository implements PanacheRepository<Institute_Master_Model> {


    public ArrayList getDetails(GetInstituteDetailsRequest request) {

        ArrayList<Institute_Master_Model> arrayList = (ArrayList<Institute_Master_Model>) list("id =?1 ", request.getInst_id());
        return arrayList;


    }
}