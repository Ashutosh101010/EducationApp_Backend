package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.NotesModel;
import com.aurd.Student.Model.Entity.StudentNotesModel;
import com.aurd.Student.Model.Entity.VideoModel;
import com.aurd.Student.Model.Request.GetFreeStudyMaterialRequest;
import com.aurd.Student.Model.Request.GetNotesRequest;
import com.aurd.Student.Model.Request.GetStudentNotesRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ApplicationScoped
public class NotesRepository implements PanacheRepository<NotesModel>
{


    public ArrayList getNotesList(int instID){
        ArrayList<NotesModel> arrayList = new ArrayList<>();
        try{
            arrayList = (ArrayList<NotesModel>) list("inst_id",instID);
            return  arrayList;
        }catch (Exception e){
              return  arrayList;
        }


    }

    public List<NotesModel> getFreeNotes(GetFreeStudyMaterialRequest request)

    {
        Query query=getEntityManager().createQuery("select NotesModel from NotesModel NotesModel left join CourseModel CourseModel on  CourseModel.id=NotesModel.course_id where NotesModel.inst_id=:instId and NotesModel.course_id=:courseId  and CourseModel.course_active=:active and NotesModel.fee_type=:type");
        query.setParameter("instId",request.getInstId().intValue());
        query.setParameter("courseId",request.getCourseId().longValue());
        query.setParameter("active",0);
        query.setParameter("type","Free");

        return query.getResultList();

    }



}
