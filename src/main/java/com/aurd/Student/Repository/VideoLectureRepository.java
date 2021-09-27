package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.VideoModel;
import com.aurd.Student.Model.Request.GetVideoLectureRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class VideoLectureRepository implements PanacheRepository <VideoModel>{

    public int getVideoLectureCount(long inst_id){
     ArrayList<VideoModel> arrayList = (ArrayList<VideoModel>) list("inst_id",inst_id);
     return  arrayList.size();
    }


    public ArrayList getVideoLectureList(long inst_id){
        ArrayList<VideoModel> arrayList = (ArrayList<VideoModel>) list("inst_id",inst_id);
        return  arrayList;
    }


    public ArrayList getCourseVideoList(GetVideoLectureRequest request){

        ArrayList<VideoModel> arrayList = new ArrayList<>();
        if(request.getFilter()==null ||request.getFilter().isEmpty() || request.getFilter().equals("")){
            arrayList = (ArrayList<VideoModel>) list("topicId=?1 and inst_id=?2",
                    request.getTopicID(),request.getInstID());

        }else if(request.getFilter().equals("free")){
            arrayList = (ArrayList<VideoModel>) list("topicId=?1 and inst_id=?2 and fee_type=?3",
                    request.getTopicID(),request.getInstID(),"Free");


        }else if(request.getFilter().equals("paid")){
            arrayList = (ArrayList<VideoModel>) list("topicId=?1 and inst_id=?2 and fee_type=?3",
                    request.getTopicID(),request.getInstID(),"Paid");


        }
        return  arrayList;
    }

}
