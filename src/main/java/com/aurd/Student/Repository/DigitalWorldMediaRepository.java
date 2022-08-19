package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.DigitalWorldMedia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import java.util.List;

@ApplicationScoped
public class DigitalWorldMediaRepository implements PanacheRepository<DigitalWorldMedia> {

    public List<DigitalWorldMedia> digitalWorldMedia(Long folderId)
    {
        Query query=getEntityManager().createQuery("select DigitalWorldMedia from DigitalWorldMedia  DigitalWorldMedia where DigitalWorldMedia.folderId=:folderId");
        List<DigitalWorldMedia> digitalWorldMediaList=find("folderId",folderId).list();
        return digitalWorldMediaList;
    }
}
