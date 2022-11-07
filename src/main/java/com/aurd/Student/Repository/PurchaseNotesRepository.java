package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.PurchaseNotes;
import com.aurd.Student.Model.Request.PurchaseNotesRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@ApplicationScoped

public class PurchaseNotesRepository  implements PanacheRepository<PurchaseNotes> {

//    public boolean Purchase(PurchaseNotesRequest request) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar now = Calendar.getInstance();
//        System.out.println(sdf.format(now.getTime()));
//
//        request.setCreated_on(Timestamp.valueOf(sdf.format(now.getTime())));
//
//        PurchaseNotesModel purchaseNotesModel = new Gson().fromJson(new Gson().toJson(request), PurchaseNotesModel.class);
//        persist(purchaseNotesModel);
//        return true;
//    }

}
