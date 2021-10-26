package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.PurchaseTestModel;
import com.aurd.Student.Model.Request.PurchaseTestSeriesRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
@ApplicationScoped

public class PurchaseTestRepository implements PanacheRepository<PurchaseTestModel> {


    public boolean Purchase(PurchaseTestSeriesRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar now = Calendar.getInstance();
        System.out.println(sdf.format(now.getTime()));

        request.setCreated_on(Timestamp.valueOf(sdf.format(now.getTime())));

        PurchaseTestModel  purchaseTestModel = new Gson().fromJson(new Gson().toJson(request), PurchaseTestModel.class);
        persist(purchaseTestModel);
        return true;
    }

}
