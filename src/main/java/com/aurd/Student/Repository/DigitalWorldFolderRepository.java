package com.aurd.Student.Repository;

import com.aurd.Student.Constant.Constants;
import com.aurd.Student.Model.Entity.DigitalWorld;
import com.aurd.Student.Model.Request.DigitalWorldFolderRequest;
import com.aurd.Student.Model.Response.DigitalWorldFolderResponse;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class DigitalWorldFolderRepository implements PanacheRepository<DigitalWorld> {

    public  List<DigitalWorld>  getDigitalWorldRootFolder(DigitalWorldFolderRequest request)
    {
        Query query=getEntityManager().createQuery("select DigitalWorld from DigitalWorld DigitalWorld where DigitalWorld.type=:type and DigitalWorld.inst_id=:id");
        query.setParameter("id",request.getInstId());
        query.setParameter("type", Constants.folderType.root.name());

        List<DigitalWorld> list=query.getResultList();

        return list;
    }


}
