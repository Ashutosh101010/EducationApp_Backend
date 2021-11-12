package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.OtpModel;
import com.aurd.Student.Model.Request.VerifyOtpRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.OtpRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/verifyOtp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class VerifyOtpController {


    @Inject
    OtpRepository otpRepository;

    @POST
    @Transactional
    public GeneralResponse verifyOtp(VerifyOtpRequest request){

      OtpModel model = otpRepository.find("otp =?1 and mobileNumber = ?2",
              request.getOtp(),request.getMobileNumber()).firstResult();
        GeneralResponse response = new GeneralResponse();
        if(model!=null){

            otpRepository.delete(model);

            response.setMessage("Otp Verification Successful");
            response.seterrorCode(0);
            response.setStatus(true);
        }else{
            response.setStatus(false);
            response.seterrorCode(1);
            response.setMessage("Failed");
        }


        return  response;
    }

}
