package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Response.LatestUpdateResponse;
import com.aurd.Student.Repository.BlogRepository;
import com.aurd.Student.Repository.CoursesRepository;
import com.aurd.Student.Repository.CurrentAffairRepository;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;

import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


@Path("/getTodaysUpdate")
public class getTodaysUpdateController {

    @Inject
    CoursesRepository coursesRepository;

    @Inject
    BlogRepository blogRepository;

    @Inject
    CurrentAffairRepository currentAffairRepository;

    @GET
    @Produces({MediaType.APPLICATION_JSON})




    @Transactional
    public LatestUpdateResponse getTodayUpdate(@QueryParam ("instID") long instID){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR ,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59 );

        String blogQuery = "SELECT * from `blog` where created_on BETWEEN ? AND ? AND inst_id = ? ;";
        Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
        blog.setParameter(1,"2021-03-13 00:00:01");
        blog.setParameter(2,"2021-03-13 23:59:59");
        blog.setParameter(3,instID);
        ArrayList<BlogModel> blogList = (ArrayList<BlogModel>) blog.getResultList();


        String caQuery = "SELECT * from `current_affairs` where created_at BETWEEN ? AND ? AND inst_id = ? ;";
        Query currentAffair = currentAffairRepository.getEntityManager().createNativeQuery(caQuery,
                CurrentAffairModel.class);
        currentAffair.setParameter(1,"2021-03-13 00:00:01");
        currentAffair.setParameter(2,"2021-03-13 23:59:59");
        currentAffair.setParameter(3,instID);
        ArrayList<CurrentAffairModel> caList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();


//
//        String query ="SELECT blog.title as blogTitle, blog.description as blogDescription , blog.id as blogID,blog.thumbnail as blogImage, blog.tags as blogTags, current_affairs.id as currentAffairID, current_affairs.title as currentAffairTitle, current_affairs.description as currentAffairDescription , current_affairs.thumbnail as currentAffairImage FROM `blog` INNER JOIN current_affairs ON current_affairs.inst_id = blog.inst_id WHERE created_on BETWEEN ? AND ? AND blog.inst_id = ? as TodaysUpdateModel;";
//
//       Query q =  blogRepository.getEntityManager()
//                .createNativeQuery(query);
//       q.setParameter(1,sdf.format(now.getTime()));
//       q.setParameter(2,sdf.format(calendar.getTime()));
//        q.setParameter(1,"2021-03-13 00:00:01");
//        q.setParameter(2,"2021-03-13 23:59:59");
//       q.setParameter(3,instID);
//
//
//     ArrayList<Object[]> arrayList = (ArrayList<Object[]>) q.getResultList();
//
//    arrayList.forEach(objects -> {
//        TodaysUpdateModel todaysUpdateModel=new TodaysUpdateModel();
//        todaysUpdateModel.setBlogTitle(objects[0].toString());
//    });



    LatestUpdateResponse response = new LatestUpdateResponse();

    response.setMessage("Get Latest Updates Successful");
    response.setStatus(true);
    response.setErrorCode(0);
    response.setBlogList(blogList);
    response.setCurrentAffairList(caList);



        return  response;


    }


}
