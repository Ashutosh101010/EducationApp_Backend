package com.aurd.Student.Repository;

import com.aurd.Student.Constant.ErrorCode;
import com.aurd.Student.Model.Entity.BookMark;


import com.aurd.Student.Model.Request.SaveUnsaveBookMarkRequest;
import com.aurd.Student.Model.Response.SaveUnsaveBookmarkResponse;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import java.sql.Timestamp;

import static io.quarkus.hibernate.orm.panache.Panache.getEntityManager;


@ApplicationScoped
public class BookMarkRepository implements PanacheRepository<BookMark> {


    public Integer saveUnsaveBookmark(SaveUnsaveBookMarkRequest request)
    {

        Query query= getEntityManager().createQuery("select BookMark from BookMark BookMark where BookMark.postId=:postId and BookMark.type=:type and BookMark.addedBy=:addedBy");

        query.setParameter("postId",request.getPostId());
        query.setParameter("type",request.getType());
        query.setParameter("addedBy",request.getAddedBy());
       BookMark bookMark= (BookMark) query.getSingleResult();
       if(bookMark==null)
       {
           bookMark=new BookMark();
           bookMark.setAddedBy(request.getAddedBy());
           bookMark.setType(request.getType());
           bookMark.setAddedOn(new Timestamp(System.currentTimeMillis()));
           bookMark.setPostId(request.getPostId());
           persist(bookMark);
          return 0;
       }
       else{
           delete(bookMark);
           return 1;
       }




}
