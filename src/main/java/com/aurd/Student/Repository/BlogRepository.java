package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Blog;
import com.aurd.Student.Model.Request.GetBlogRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@ApplicationScoped
public class BlogRepository implements PanacheRepository<Blog> {
//
//    public ArrayList getBlogs(GetBlogRequest request) {
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//
//        if (request.getDate().isEmpty() || request.getDate().equals("")) {
//
//
//            String lastId;
//            if (request.getLastId()==null || request.getLastId().equals("")) {
//                lastId = formatter.format(calendar.getTime());
//            } else {
//                lastId = request.getLastId();
//            }
//
////              ArrayList<BlogModel> arrayList = (ArrayList<BlogModel>) list(" inst_id and created_on < lastId order By created_on desc",
////                      request.getInst_id(),lastId);
////            return  arrayList;
//
//
//
//            Query query = getEntityManager().createQuery("select Blog from BlogModel Blog where   Blog.inst_id = : inst_id" +
//                    " and Blog.created_on < : lastId  order by Blog.created_on desc");
//            query.setParameter("inst_id", request.getInst_id());
//            query.setParameter("lastId", Timestamp.valueOf(lastId));
//
//
//             return  (ArrayList<Blog>) query.setMaxResults(5).getResultList();
//
//        } else {
//
//            if (request.getDate().equals(formatter.format(calendar.getTime()))) {
//                String lastId;
//                if (request.getLastId().equals("")) {
//                    lastId = formatter.format(calendar.getTime());
//                } else {
//                    lastId = request.getLastId();
//                }
//
//                Query query = getEntityManager().createQuery("select Blog from Blog Blog where   Blog.inst_id = : inst_id" +
//                        " and Blog.created_on < : lastId  order by Blog.created_on desc");
//                query.setParameter("inst_id", request.getInst_id());
//                query.setParameter("lastId", Timestamp.valueOf(lastId));
//
//
//                return  (ArrayList<Blog>) query.setMaxResults(5).getResultList();
////                ArrayList<BlogModel> arrayList =
////                        (ArrayList<BlogModel>) list("inst_id", request.getInst_id());
////                return arrayList;
//
//            } else {
//                String start = request.getDate() + " 00:00:00";
//                String end = request.getDate() + " 23:59:59";
//                String lastId;
//                if (request.getLastId().equals("")) {
//                    lastId = formatter.format(calendar.getTime());
//                } else {
//                    lastId = request.getLastId();
//                }
//
//                Query query = getEntityManager().createQuery("select Blog from BlogModel Blog where   Blog.inst_id = : inst_id" +
//                        " and Blog.created_on < : lastId and Blog.created_on between :startDate and :endDate order by Blog.created_on desc");
//                query.setParameter("inst_id", request.getInst_id());
//                query.setParameter("lastId", Timestamp.valueOf(lastId));
//                query.setParameter("startDate",start);
//                query.setParameter("endDate",end);
//
//
////
////                String blogQuery = "SELECT * from `blog` where created_on BETWEEN ? AND ? AND inst_id = ? ;";
////                Query blog = getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
////                blog.setParameter(1, start);
////                blog.setParameter(2, end);
////                blog.setParameter(3, request.getInst_id());
//                ArrayList<Blog> blogList = (ArrayList<Blog>) query.setMaxResults(5).getResultList();
//
//                return blogList;
//            }
//
//
//        }
//
//
//    }


}
