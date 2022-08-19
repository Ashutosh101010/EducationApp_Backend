package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Audio;
import com.aurd.Student.Model.Request.GetAudioNotesRequest;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class AudioNotesRepository implements PanacheRepository<Audio> {

    public int getAudioNotesCount(long inst_id){
        ArrayList<Audio> arrayList = (ArrayList<Audio>) list("inst_id",inst_id);
        return  arrayList.size();
    }


    public ArrayList getAudioNotesList(Long inst_id){
        ArrayList<Audio> arrayList = (ArrayList<Audio>) list("inst_id",inst_id.intValue());
        return  arrayList;
    }


    public ArrayList getCourseAudioList(GetAudioNotesRequest request){
        Integer val = Math.toIntExact(request.getInstID());
        ArrayList<Audio> arrayList = new ArrayList<>();
        if(request.getFilter()==null ||request.getFilter().isEmpty() || request.getFilter().equals("")){
            arrayList = (ArrayList<Audio>) list("topicId=?1 and inst_id=?2 ORDER BY created_at DESC",
                    request.getTopicID(),val);

        }else if(request.getFilter().equals("free")){
            arrayList = (ArrayList<Audio>) list("topicId=?1 and inst_id=?2 and fee_type=?3 ORDER BY created_at DESC",
                    request.getTopicID(),val,"Free");


        }else if(request.getFilter().equals("paid")){
            arrayList = (ArrayList<Audio>) list("topicId=?1 and inst_id=?2 and fee_type=?3 ORDER BY created_at DESC",
                    request.getTopicID(),val,"Paid");


        }
        return  arrayList;
    }



}
