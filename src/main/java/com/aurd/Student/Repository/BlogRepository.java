package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Request.GetBlogRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.ArrayList;

@ApplicationScoped
public class BlogRepository implements PanacheRepository<BlogModel> {

    public ArrayList getBlogs(GetBlogRequest request){

        if(request.getDate().isEmpty()|| request.getDate().equals("")){
            ArrayList<BlogModel> arrayList = (ArrayList<BlogModel>) list("inst_id",request.getInst_id());
            return  arrayList;
        }else{
            String start = request.getDate()+" 00:00:00";
            String end =  request.getDate()+" 23:59:59";

            String blogQuery = "SELECT * from `blog` where created_on BETWEEN ? AND ? AND inst_id = ? ;";
            Query blog = getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
            blog.setParameter(1,start);
            blog.setParameter(2,end);
            blog.setParameter(3,request.getInst_id());
            ArrayList<BlogModel> blogList = (ArrayList<BlogModel>) blog.getResultList();

            return  blogList;
        }


    }




}
