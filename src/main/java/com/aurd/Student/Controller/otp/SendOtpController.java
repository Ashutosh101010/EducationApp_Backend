package com.aurd.Student.Controller.otp;

import com.aurd.Student.Model.Entity.OtpModel;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.SendOtpRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.OtpRepository;
import com.aurd.Student.Repository.StudentRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Random;
import com.aurd.Student.Constant.Constants;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;


@Path("/sendOtp")
public class SendOtpController {

    @Inject
    StudentRepository repository;

    @Inject
    OtpRepository otpRepository;



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public GeneralResponse sendOtp(SendOtpRequest request){
        GeneralResponse response= new GeneralResponse();


        try{
            StudentModel model = repository.find("contact",
                    request.getMobileNumber().trim()).firstResult();

            if(model==null){
                String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
                System.out.println(otp);


                String text = "Welcome, your onetime verification code is " +otp+
                        " regards - backyard picaso private limited.";

                String msgUrl="https://www.smsgatewayhub.com/api/mt/SendSMS?" +
                        "APIKey="+Constants.apiKey+"&senderid="+Constants.senderId+
                        "&channel=2&DCS=0&flashsms=0&number="+
                        "91"+request.getMobileNumber()+"&text="+ URLEncoder.encode(text)+
                        "&route=clickhere&EntityId="+Constants.entityId+
                        "&dlttemplateid="+Constants.dltTemplateId;


                System.out.println(msgUrl);

                HttpGet httpGet= new HttpGet(msgUrl);
                CloseableHttpClient client = HttpClientBuilder.create().build();
                CloseableHttpResponse httpResponse = client.execute(httpGet);

                int i=0;
                InputStream io = httpResponse.getEntity().getContent();
                String resp ="";

                while ((i = io.read())!=-1){
                    resp = resp+(char)i;
                }





                JSONObject jsonObject= new JSONObject(resp);
                System.out.println(jsonObject);

                if(jsonObject.getString("ErrorCode").equals("000")
                &&jsonObject.getString("ErrorMessage").equals("Success")){

                    OtpModel otpModel  = new OtpModel();
                    otpModel.setOtp(otp);
                    otpModel.setMobileNumber(request.getMobileNumber());
                    otpRepository.persist(otpModel);


                    response.setStatus(true);
                    response.seterrorCode(0);
                    response.setMessage("Otp send successfully");
                }else{
                    response.setStatus(false);
                    response.seterrorCode(3);
                    response.setMessage("Something went wrong Otp cannot send");
                }



            }else{
                response.setStatus(false);
                response.seterrorCode(1);
                response.setMessage("Mobile Number Already Existed");
            }

            return response;


        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            response.setStatus(false);
            response.seterrorCode(2);
            response.setMessage("Something went wrong");

            return response;

        }


    }
}
