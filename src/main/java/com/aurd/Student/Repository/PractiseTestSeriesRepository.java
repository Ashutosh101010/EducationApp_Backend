package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.PractiseTestSeriesModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;


import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;


@ApplicationScoped

public class PractiseTestSeriesRepository  implements PanacheRepository<PractiseTestSeriesModel>{
    public ArrayList getPractiseTest(int instID, int testID){
        ArrayList<PractiseTestSeriesModel> arrayList = (ArrayList<PractiseTestSeriesModel>)
                find("inst_id =?1 and topic_id = ?2",instID,testID).list();
        return  arrayList;
    }


}
