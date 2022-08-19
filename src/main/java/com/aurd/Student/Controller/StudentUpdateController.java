package com.aurd.Student.Controller;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.UpDateRequest;
import com.aurd.Student.Model.Response.UpdateResponse;
import com.aurd.Student.Repository.StudentRepository;
import com.google.gson.Gson;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;


import javax.inject.Inject;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static com.aurd.Service.bucket;
import static com.aurd.Service.s3;

@Path("/updateProfile")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class StudentUpdateController {
    @Inject
    StudentRepository repository;
    @POST

    @Transactional


    public UpdateResponse update(@MultipartForm MultipartFormDataInput inputRequest) throws ParseException {


        final String bucketName = bucket;

        Map<String, List<InputPart>> input = inputRequest.getFormDataMap();
        UpDateRequest request = new UpDateRequest();
        UpdateResponse response = new UpdateResponse();




        try {


            System.out.println(input.get("pic"));
            System.out.println(input.get("bio").get(0).getBodyAsString());

            System.out.println(input.get("gender").get(0).getBodyAsString());

            System.out.println(input.get("state_id").get(0).getBodyAsString());
            System.out.println(input.get("dob").get(0).getBodyAsString());
            System.out.println(input.get("cityId").get(0).getBodyAsString());

            System.out.println(input.get("address").get(0).getBodyAsString());


            Long studId = Long.parseLong(input.get("stud_id").get(0).getBodyAsString());

            StudentModel studentModel = repository.find("id",studId).firstResult();

            if(input.get("bio")!=null){
                if(input.get("bio").equals("")){
                    request.setBio(null);

                }else{
                    request.setBio(input.get("bio").get(0).getBodyAsString());

                }
            }else{
                request.setBio(studentModel.getBio());
            }

            if(input.get("gender")!=null){
                request.setGender(input.get("gender").get(0).getBodyAsString());

            }else{
                request.setGender(studentModel.getGender());
            }


            if(input.get("state_id")!=null){
                if(input.get("state_id").get(0).getBodyAsString().equals("0")){
                    request.setState_id(null);
                }else{
                    request.setState_id(Integer.parseInt(input.get("state_id").get(0).getBodyAsString()));
                }

            }else{
                request.setState_id(studentModel.getStateId());
            }

            if(input.get("dob")!=null){

                request.setDOB(input.get("dob").get(0).getBodyAsString());

            }else{
                request.setDOB(studentModel.getDob().toString());
            }

            if(input.get("cityId")!=null){
                request.setCityId(Integer.valueOf(input.get("cityId").get(0).getBodyAsString()));

            }else{
                request.setCityId(studentModel.getDistrictId());
            }

            if(input.get("address")!=null){
                request.setAddress(input.get("address").get(0).getBodyAsString());
            }else{
                request.setAddress(studentModel.getAddress());

            }


//            if(input.get("bio").get(0)!=null){
//
//            }
//
//
//            if(input.get("gender").get(0)!=null){
//              }
//
//            if(input.get("state_id").get(0)!=null){
//
//            }
//
//
//            if(input.get("dob").get(0)!=null){
//             }
//
//            if(input.get("district_id").get(0)!=null ){
//            }
//            if(input.get("address").get(0)!=null){
//
//            }

            request.setStudent_id(studId);
            if(input.get("name")!=null){
                request.setF_name(input.get("name").get(0).getBodyAsString());
            }else{
                request.setF_name(studentModel.getFname());
            }

            if(input.get("mobileNumber")!=null){
                request.setMobile_no(input.get("mobileNumber").get(0).getBodyAsString());
            }else{
                request.setMobile_no(studentModel.getContact());
            }

            if(input.get("email")!=null){
                request.setEmail(input.get("email").get(0).getBodyAsString());
            }else{
                request.setEmail(studentModel.getEmail());

            }


            if(input.get("pic")==null){
                System.out.println("Pic is Null");
                if(studentModel.getProfile()!=null){
                    request.setImageId(studentModel.getProfile());
                }else{
                    request.setImageId(null);
                }

            }
            else{
                System.out.println("Pic is not null");
                request.setPic(input.get("pic").get(0).getBody(InputStream.class,null));
                System.out.println(request);
                byte imageBytes[] = request.getPic().readAllBytes();
                System.out.println(imageBytes);
                System.out.println(imageBytes.length);

                InputStream inputStream = new ByteArrayInputStream(imageBytes);
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(imageBytes.length);

                String ImageId = String.valueOf(System.currentTimeMillis());
                String key = "image/"+ImageId;
                s3.putObject(bucketName, key, inputStream,metadata );


                request.setImageId(ImageId);
                System.out.println(ImageId);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

//            System.out.println(new Gson().toJson(request));

            Integer resp  = repository.UpDateRequest(request);
            System.out.println(studentModel);

            if(resp>0)
            {
                response.setMessage("update Success");
                response.setStatus(true);
                response.seterrorCode(0);
            }
            else{
                response.setMessage("update Fail");
                response.setStatus(false);
                response.seterrorCode(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


       return response;
    }
    }
