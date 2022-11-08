package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.VideoModel;
import com.aurd.Student.Model.Request.GetFreeStudyMaterialRequest;
import com.aurd.Student.Model.Request.GetVideoLectureRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class VideoLectureRepository implements PanacheRepository <VideoModel>{

    public int getVideoLectureCount(long inst_id){
     ArrayList<VideoModel> arrayList = (ArrayList<VideoModel>) list("inst_id",inst_id);
     return  arrayList.size();
    }


    public ArrayList getVideoLectureList(Integer inst_id){
        ArrayList<VideoModel> arrayList = (ArrayList<VideoModel>) list("inst_id",inst_id);
        return  arrayList;
    }


    public ArrayList getCourseVideoList(GetVideoLectureRequest request){
Integer val = Math.toIntExact(request.getInstID());
        ArrayList<VideoModel> arrayList = new ArrayList<>();
        if(request.getFilter()==null ||request.getFilter().isEmpty() || request.getFilter().equals("")){
            arrayList = (ArrayList<VideoModel>) list("topicId=?1 and inst_id=?2 ORDER BY created_at DESC",
                    request.getTopicID(),val);

        }else if(request.getFilter().equals("free")){
            arrayList = (ArrayList<VideoModel>) list("topicId=?1 and inst_id=?2 and fee_type=?3 ORDER BY created_at DESC",
                    request.getTopicID(),val,"Free");


        }else if(request.getFilter().equals("paid")){
            arrayList = (ArrayList<VideoModel>) list("topicId=?1 and inst_id=?2 and fee_type=?3 ORDER BY created_at DESC",
                    request.getTopicID(),val,"Paid");


        }
        return  arrayList;
    }


    public List<VideoModel> getFreeVideos(GetFreeStudyMaterialRequest request)

    {
        Query query=getEntityManager().createQuery("select VideoModel from VideoModel VideoModel left join CourseModel CourseModel on CourseModel.id=VideoModel.course_id  where VideoModel.inst_id=:instId and VideoModel.course_id=:courseId and CourseModel.course_active=:active and VideoModel.fee_type=:type");
        query.setParameter("instId",request.getInstId().intValue());
        query.setParameter("courseId",request.getCourseId().longValue());
        query.setParameter("active",0);
        query.setParameter("type","Free");

        return query.getResultList();

    }

}
