package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.FunBox;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FunBoxRepository implements PanacheRepository<FunBox> {

    public List<FunBox> getFunList(int page,int pageSize,int inst_id){

        List<FunBox> list= (ArrayList<FunBox>) find("inst_id=?1 ORDER BY id desc",inst_id).page(page,pageSize).list();
        return list;
    }

}
