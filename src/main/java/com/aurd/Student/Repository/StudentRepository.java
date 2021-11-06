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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<StudentModel> {

    public StudentModel login(LoginRequest request){
        try {
            return  find("contact=?1 and password = ?2 and inst_id = ?3",
                    request.getContact(),request.getPassword(),request.getInst_id()).firstResult();
        }catch (Exception e){
            e.printStackTrace();
            return  new StudentModel();
        }

    }

    public Integer UpDateRequest(UpDateRequest request) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date langDate = sdf.parse(request.getDOB());


        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = formatter.parse(request.getDOB());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

//        String imageid = "";
//        if(request.getImageId()!=null){
//            imageid= request.getImageId();
//        }else{
//            imageid = request.getImage();
//        }

   return update("fname=?1 , contact=?2   ,address=?3 ," +
                   "email=?4 , stateId=?5   ,districtId=?6 ,dob=?7, bio=?8 , gender=?9, profile=?10 " +
                   " where id=?11"
        ,request.getF_name()
        ,request.getMobile_no()
        ,request.getAddress()
        ,request.getEmail()
        ,request.getState_id()
        ,request.getDistrict_id()
        ,sqlDate,
           request.getBio(),
        request.getGender(),request.getImageId()
        ,request.getStudent_id()

        );
    }


    public StudentModel getDetails(GetStudentDetailRequest request){
        StudentModel model = find("id =?1 and username = ?2",
                request.getId(),request.getUsername()).firstResult();
        return  model;
    }



}
