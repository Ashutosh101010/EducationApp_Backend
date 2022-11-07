package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.Enquiry;

import com.aurd.Student.Model.Request.EnquiryRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@ApplicationScoped

public class EnquiryRepository implements PanacheRepository<Enquiry> {



//
//    public boolean Enquiry(EnquiryRequest request) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar now = Calendar.getInstance();
//        System.out.println(sdf.format(now.getTime()));
//
//        System.out.println(new Gson().toJson(request));
//
//        request.setCreated_at(Timestamp.valueOf(sdf.format(now.getTime())));
//        request.setUpdated_at(Timestamp.valueOf(sdf.format(now.getTime())));
//
//        EnquiryModel enquiryModel = new Gson().fromJson(new Gson().toJson(request), EnquiryModel.class);
//        persist(enquiryModel);
//        return true;
//    }
//



}
