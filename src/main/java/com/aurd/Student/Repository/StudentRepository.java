package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.GetProfileRequest;
import com.aurd.Student.Model.Request.GetStudentDetailRequest;
import com.aurd.Student.Model.Request.LoginRequest;
import com.aurd.Student.Model.Request.UpDateRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<StudentModel> {

    public StudentModel login(LoginRequest request){
        return  find("email=?1 and password = ?2",request.getEmail(),request.getPassword()).firstResult();
    }

    public Integer UpDateRequest(UpDateRequest request) {


   return update("fname=?1 , contact=?2   ,address=?3 ,email=?4 , stateId=?5   ,districtId=?6 ,dob=?7  where id=?8"
        ,request.getF_name()
        ,request.getMobile_no()
        ,request.getAddress()
        ,request.getEmail()
        ,request.getState_id()
        ,request.getDistrict_id()
         ,request.getDOB()
            //   , date
           ,request.getStudent_id()

        );
    }


    public StudentModel getDetails(GetStudentDetailRequest request){
        StudentModel model = find("id =?1 and username = ?2",request.getId(),request.getUsername()).firstResult();
        return  model;
    }



}
