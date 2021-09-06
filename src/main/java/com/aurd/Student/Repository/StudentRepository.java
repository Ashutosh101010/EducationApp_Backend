package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.GetProfileRequest;
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
    // public int update(UpDateRequest request) {
      //  return  update("name=' ' where id=?1" ,request.getStudent_id());

   //  }

    @Transactional
    public Integer UpDateRequest(UpDateRequest request) {

     //  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date=LocalDate.now();

        //LocalDate date = LocalDate.parse(string, formatter);
        System.out.println(date);


        //(
       // String pattern = "MM/dd/yyyy";
       // DateFormat df = new SimpleDateFormat(pattern);
        //)


   return update("fname=?1 , contact=?2   ,address=?3 ,stateId=?4   ,districtId=?5   where id=?6"
        ,request.getFather_name()
        ,request.getMobile_no()
        ,request.getAddress()
        ,request.getState_id()
        ,request.getDistrict_id()

       //  ,request.getDOB()
//                , date
                ,request.getStudent_id()

        );
    }


    public StudentModel profile(GetProfileRequest request)


    {

    return find("email =?1",request.getEmail()).firstResult();
    }


}
