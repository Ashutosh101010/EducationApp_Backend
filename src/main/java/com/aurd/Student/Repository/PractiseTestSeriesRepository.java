package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.TestSeries;
import com.aurd.Student.Model.Request.testseries.Get_PractiseTestSeries_Request;
import io.quarkus.hibernate.orm.panache.PanacheRepository;


import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;


@ApplicationScoped

public class PractiseTestSeriesRepository  implements PanacheRepository<TestSeries>{

//
//    public ArrayList getTestSeries_PractiseTest(Get_PractiseTestSeries_Request request){
//        ArrayList<PractiseTestSeriesModel> list = (ArrayList<PractiseTestSeriesModel>)
//                find("series_id ",request.getTest_series_id()).list();
//        return list;
//
//    }
//    public ArrayList getTestSeries_PractiseTestByTest(Get_PractiseTestSeries_Request request,String testName){
//        ArrayList<PractiseTestSeriesModel> list = (ArrayList<PractiseTestSeriesModel>)
//                find("series_id=?1 and test=?2",request.getTest_series_id(),testName).list();
//        return list;
//
//    }
//
//    public ArrayList getTestSeries_PractiseTestBySeries(Get_PractiseTestSeries_Request request){
//        ArrayList<PractiseTestSeriesModel> list = (ArrayList<PractiseTestSeriesModel>)
//                find("series_id=?1",request.getTest_series_id()).list();
//        return list;
//
//    }


}
