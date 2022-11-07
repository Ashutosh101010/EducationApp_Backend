package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Session;
import com.aurd.security.Security;
import com.aurd.security.Token;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
public class SessionRepository implements PanacheRepository<Session> {

    @Inject
    Security security;


    @ConfigProperty(name = "tokenExpiry")
    Long tokenExpiry;

//
    @Transactional
    public Session createSession(Long userId,String userType){

        Session session=find("user_id=?1 and user_type=?2",userId,userType).firstResult();
        Token token=security.generateToken(userId,userType);
        if(session==null)
        {


            Session ses=new Session();
            ses.setExpiry(token.getExpiry());
            ses.setTimestamp(token.getTimestamp());
            ses.setToken(token.getStringToken());
            ses.setId(userId);
            ses.setUserType(userType);

            persist(ses);
            return ses;
        }
        else{

            session.setTimestamp(token.getTimestamp());
            session.setExpiry(token.getExpiry());
            session.setToken(token.getStringToken());
            Query query=getEntityManager().createQuery("update Session Session set Session.expiry=:expiry, Session.timestamp=:timestamp where Session.id=:id ");
            query.setParameter("timestamp",token.getTimestamp());
            query.setParameter("expiry",token.getExpiry());
            query.setParameter("id",session.getId());
            query.executeUpdate();
            return session;

        }
    }
    public Session fetchSession(Long userId,String userType)
    {
       Session session=find("user_id=?1 and user_type=?2",userId,userType).firstResult();
       return session;
    }

    @Transactional
    public void updateExpiry(String token)
    {
        Session session=find("token=?1",token).firstResult();
        if(session!=null)
        {
            update("expiry=?1",session.getExpiry()+tokenExpiry);
        }
    }
}
