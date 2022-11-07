package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Course;
import com.aurd.Student.Model.Request.GetCoursesRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.jboss.resteasy.annotations.Query;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class CoursesRepository implements PanacheRepository<Course> {



//    public ArrayList getCourses(int instID){
//        ArrayList<CourseModel> arrayList = new ArrayList();
//      List<CourseModel> list = list("inst_id =?1 and course_active=?2",instID,1);
//        arrayList.addAll(list);
//
//        return  arrayList;
//    }
//
//
//    public CourseModel getCourseDetails(long instituteId, long courseID){
//        System.out.println(find("id",courseID).firstResult());
//
//        return find("id",courseID).firstResult();
//
//
//    }




}
