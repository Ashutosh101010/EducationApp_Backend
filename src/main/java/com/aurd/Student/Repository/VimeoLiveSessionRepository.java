package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.VimeoLiveSession;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class VimeoLiveSessionRepository implements PanacheRepository<VimeoLiveSession> {
//    @Transactional
//    public void addSession(VimeoLiveSession vimeoLiveSession)
//    {
//        persistAndFlush(vimeoLiveSession);
//    }
}
