package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.RunningBatches;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class RunningBatchRepository implements PanacheRepository<RunningBatches> {

//    public ArrayList getBatchesList(long inst_id, long course_id){
//
//        ArrayList<RunningBatchesModel> arrayList = (ArrayList<RunningBatchesModel>)
//                find("id=?1 and inst_id=?2",course_id,inst_id).list();
//
//        return  arrayList;
//
//    }



}
