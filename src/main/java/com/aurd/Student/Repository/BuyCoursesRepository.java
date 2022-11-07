package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.StudentCourse;
import com.aurd.Student.Model.Request.BuyCoursesRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@ApplicationScoped


public class BuyCoursesRepository implements PanacheRepository<StudentCourse> {


//
//    public boolean BuyCourses(BuyCoursesRequest request) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar now = Calendar.getInstance();
//        System.out.println(sdf.format(now.getTime()));
//
//
//        request.setCreated_at(Timestamp.valueOf(sdf.format(now.getTime())));
//        request.setUpdated_at(Timestamp.valueOf(sdf.format(now.getTime())));
//
//        StudentCourseModel studentCourseModel = new Gson().fromJson
//                (new Gson().toJson(request), StudentCourseModel.class);
//        persist(studentCourseModel);
//        return true;
//    }
//

}
