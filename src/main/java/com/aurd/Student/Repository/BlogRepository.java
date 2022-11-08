package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Request.GetBlogRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@ApplicationScoped
public class BlogRepository implements PanacheRepository<BlogModel> {

    public ArrayList getBlogs(GetBlogRequest request) {

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();

        Query query=null;

        if (request.getDate()==null || request.getDate().isEmpty()) {


            query = getEntityManager().createQuery("select Blog from BlogModel Blog where   Blog.inst_id = : inst_id" +
                    " order by Blog.created_on desc");
            query.setParameter("inst_id", request.getInst_id());




        } else {
                String start = request.getDate() + " 00:00:00";
                String end = request.getDate() + " 23:59:59";


                query = getEntityManager().createQuery("select Blog from BlogModel Blog where   Blog.inst_id = : inst_id" +
                        " and Blog.created_on between :startDate and :endDate order by Blog.created_on desc");
                query.setParameter("inst_id", request.getInst_id());
                query.setParameter("startDate",new Date(start));
                query.setParameter("endDate",new Date(end));

            }


        query.setMaxResults(request.getPageCount());
        query.setFirstResult(request.getPage()* request.getPageCount());
        return  (ArrayList<BlogModel>) query.getResultList();

        }


    }

