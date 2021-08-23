package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Request.GetBlogRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class BlogRepository implements PanacheRepository<BlogModel> {

    public ArrayList getBlogs(GetBlogRequest request){

        ArrayList<BlogModel> arrayList = (ArrayList<BlogModel>) list("inst_id",request.getInst_id());
        return  arrayList;
    }




}
