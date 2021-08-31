package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.BookMarkModel;

import com.aurd.Student.Model.Request.GetBookMarkRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class BookMarkRepository implements PanacheRepository<BookMarkModel> {

    public ArrayList getBooks(GetBookMarkRequest request){

        ArrayList<BookMarkModel> arrayList = (ArrayList<BookMarkModel>) list("added_by =?1 and inst_id =?2" ,request.getStudent_id(),request.getInst_id());
        return  arrayList;
    }
}
