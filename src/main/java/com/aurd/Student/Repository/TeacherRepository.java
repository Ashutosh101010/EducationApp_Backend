package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Employee;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.hibernate.sql.Select;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Employee> {
//
//    public TeacherModel findTeacher(long id){
//        return  find("id",id).firstResult();
//    }
//

}
