package com.aurd.Student.Repository;



import com.aurd.Student.Model.Entity.NoticeModel;
import com.aurd.Student.Model.Request.GetNoticeRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetNoticeRepository implements PanacheRepository<NoticeModel> {

    public ArrayList getNotice(GetNoticeRequest request){



        ArrayList<NoticeModel> arrayList = (ArrayList<NoticeModel>) list("inst_id =?1 ", request.getInst_id());
        return arrayList;

    }



}
