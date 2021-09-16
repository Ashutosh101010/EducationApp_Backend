package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.VideoModel;
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


    public ArrayList getCourseVideoList(long topicId,long instId){
        ArrayList<VideoModel> arrayList = (ArrayList<VideoModel>) list("topicId=?1 and inst_id=?2",
                topicId,instId);
        return  arrayList;
    }

}
