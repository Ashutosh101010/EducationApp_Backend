package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.DigitalWorldMedia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import java.util.List;

@ApplicationScoped
public class DigitalWorldMediaRepository implements PanacheRepository<DigitalWorldMedia> {

    public List<DigitalWorldMedia> digitalWorldMedia(Long folderId , Long instId)
    {
        Query query=getEntityManager().createQuery("select DigitalWorldMedia from DigitalWorldMedia  DigitalWorldMedia where DigitalWorldMedia.folderId=:folderId and DigitalWorldMedia.inst_id=:instId");
       query.setParameter("folderId",folderId);
       query.setParameter("instId",instId);

       List<DigitalWorldMedia> list=query.getResultList();

        return list;
    }


}
