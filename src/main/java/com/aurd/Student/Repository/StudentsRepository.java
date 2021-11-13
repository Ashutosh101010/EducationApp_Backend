//package com.aurd.Student.Repository;
//
//
//import com.aurd.Student.Model.Students;
//import io.quarkus.hibernate.orm.panache.PanacheRepository;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.persistence.Query;
//
//@ApplicationScoped
//
//public class StudentsRepository implements PanacheRepository<Students> {
//
//    public int updateStudent(StudentRequest request) {
//
//        Query query = getEntityManager().createQuery("update Students Students set " +
//                "Students.deviceId=:deviceid where " + "Students.id=:id ");
//        query.setParameter("deviceid", request.getDeviceId());
//        query.setParameter("id", request.getUserId());
//        return query.executeUpdate();
//
//    }
//
//}
