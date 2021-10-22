package com.aurd.Student.Controller;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.UpDateRequest;
import com.aurd.Student.Model.Response.UpdateResponse;
import com.aurd.Student.Repository.StudentRepository;
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


        final String bucketName = "educationapp";

        Map<String, List<InputPart>> input = inputRequest.getFormDataMap();
        UpDateRequest request = new UpDateRequest();
        UpdateResponse response = new UpdateResponse();

        try {

            if(input.get("dob")!=null){
                request.setDOB(input.get("dob").get(0).getBodyAsString());
            }
            if(input.get("address")!=null){
                request.setAddress(input.get("address").get(0).getBodyAsString());
            }
            if(input.get("bio")!=null){

            }

            request.setBio(input.get("bio").get(0).getBodyAsString());
            request.setStudent_id(Long.parseLong(input.get("stud_id").get(0).getBodyAsString()));
            request.setDistrict_id(input.get("district_id").get(0).getBodyAsString());
            request.setF_name(input.get("name").get(0).getBodyAsString());
            request.setGender(input.get("gender").get(0).getBodyAsString());
            request.setMobile_no(input.get("mobileNumber").get(0).getBodyAsString());
            request.setState_id(Integer.parseInt(input.get("state_id").get(0).getBodyAsString()));
            request.setEmail(input.get("email").get(0).getBodyAsString());


            if(input.get("pic")==null){
                request.setPic(null);
            }
            else{
                request.setPic(input.get("pic").get(0).getBody(InputStream.class,null));
                System.out.println(request);
                byte imageBytes[] = request.getPic().readAllBytes();
                System.out.println(imageBytes);
                System.out.println(imageBytes.length);

                InputStream inputStream = new ByteArrayInputStream(imageBytes);
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(imageBytes.length);

                String ImageId = String.valueOf(System.currentTimeMillis());
                s3.putObject(bucketName, ImageId, inputStream,metadata );


                request.setImageId(ImageId);
                System.out.println(ImageId);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            if(input.get("image")==null){
                request.setImage(null);
            }else {

                request.setImage(input.get("image").get(0).getBodyAsString());
            }




            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println((sdf.format(new Date())).toString());


            Integer studentModel  = repository.UpDateRequest(request);
            System.out.println(studentModel);

            if(studentModel>0)
            {
                response.setMessage("update Success");
                response.setStatus(true);
                response.setStatusCode(0);
            }
            else{
                response.setMessage("update Fail");
                response.setStatus(false);
                response.setStatusCode(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


       return response;
    }
    }
