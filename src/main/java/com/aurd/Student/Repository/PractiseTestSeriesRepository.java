package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.PractiseTestSeriesModel;
import com.aurd.Student.Model.Request.testseries.Get_PractiseTestSeries_Request;
import io.quarkus.hibernate.orm.panache.PanacheRepository;


import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;


@ApplicationScoped

public class PractiseTestSeriesRepository  implements PanacheRepository<PractiseTestSeriesModel>{


    public ArrayList getTestSeries_PractiseTest(Get_PractiseTestSeries_Request request){
        ArrayList<PractiseTestSeriesModel> list = (ArrayList<PractiseTestSeriesModel>)
                find("series_id",request.getTest_series_id()).list();
        return list;

    }


}
