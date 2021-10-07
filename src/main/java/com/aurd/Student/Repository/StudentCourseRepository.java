package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.StudentCourseModel;
import com.aurd.Student.Model.Request.GetStudentCourseRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class StudentCourseRepository implements PanacheRepository<StudentCourseModel> {

    public ArrayList getStudentCourses(GetStudentCourseRequest request){
        try {
            ArrayList<StudentCourseModel> arrayList = (ArrayList<StudentCourseModel>)
                    list("inst_id =?1 and userId=?2 ORDER BY created_at DESC " ,
                            request.getInst_id(),request.getUserId());

            System.out.println(arrayList.size());

            return  arrayList;
        }catch (Exception e){
            e.printStackTrace();
            return  new ArrayList();

        }


    }




}
