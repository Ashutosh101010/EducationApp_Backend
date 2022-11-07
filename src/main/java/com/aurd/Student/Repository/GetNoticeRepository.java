package com.aurd.Student.Repository;



import com.aurd.Student.Model.Entity.Notice;
import com.aurd.Student.Model.Request.GetNoticeRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetNoticeRepository implements PanacheRepository<Notice> {

//    public ArrayList getNotice(GetNoticeRequest request){
//        ArrayList<NoticeModel> arrayList = (ArrayList<NoticeModel>)
//                list("inst_id=?1 ORDER BY created_on DESC ", request.getInst_id());
//        return arrayList;
//    }



}
