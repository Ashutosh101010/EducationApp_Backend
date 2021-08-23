package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.GetCoursesRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class CoursesRepository implements PanacheRepository<CourseModel> {



    public ArrayList getCourses(int instID){
        ArrayList<CourseModel> arrayList = new ArrayList();
      List<CourseModel> list = list("inst_id",instID);
        arrayList.addAll(list);

        return  arrayList;
    }


    public CourseModel getCourseDetails(long instituteId, long courseID){
        System.out.println(find("id",courseID).firstResult());

        return find("id",courseID).firstResult();


    }

    public ArrayList getTodayUpdateData(int instid){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        System.out.println(sdf.format(now.getTime()));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR ,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59 );


        Map<Integer, Object>  map = new HashMap<>();
        map.put(1,instid);
        map.put(2,sdf.format(now.getTime()));
        map.put(2,sdf.format(calendar.getTime()));

        find("created_at between =?1 and =?2",sdf.format(now.getTime()),sdf.format(calendar.getTime())).list();

        return new ArrayList();
    }


}
