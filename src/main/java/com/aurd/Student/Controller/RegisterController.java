package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.RegisterRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterController {

    @Inject
    StudentRepository repository;
    @POST
    @Transactional
    public GeneralResponse register(RegisterRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        GeneralResponse generalResponse = new GeneralResponse();
        Gson gson = new Gson();
        StudentModel studentModel = gson.fromJson(gson.toJson(request),StudentModel.class);
        studentModel.setPassword(request.getPassword());
        studentModel.setAddress(null);
        studentModel.setBio(null);
        studentModel.setCoursecheck(0);
        studentModel.setDistrictId(null);
        studentModel.setEmail_verify(0);
        studentModel.setGender(null);
        studentModel.setHostel(0);
        studentModel.setTownId(null);
        studentModel.setLogin_status(1);
        studentModel.setProfile(null);
        studentModel.setReg_status(request.getReg_status());

        studentModel.setLibrary(0);
        studentModel.setPhone_verify(0);
        studentModel.setUsername(request.getContact());
        studentModel.setDeviceId(null);
        studentModel.setCreated_at(Timestamp.valueOf(sdf.format(cal.getTime())));
        studentModel.setIs_active(false);
        studentModel.setInst_id(request.getInst_id());
        studentModel.setDob(request.getDob());
        studentModel.setStateId(request.getStateId());
        studentModel.setDistrictId(request.getCityId());
        studentModel.setPassword_salt("");

//
//        String passwordToHash =request.getPassword();
//        byte[] salt = getSalt();
//        System.out.println(salt.toString());
//
//        String securePassword = get_SHA_1_SecurePassword(passwordToHash, salt);
//        System.out.println(securePassword);

//            System.out.println(pass);
//            SecureRandom secureRandom = new SecureRandom();
//            byte[] salt = new byte[16];
//            secureRandom.nextBytes(salt);
//            System.out.println(secureRandom.toString());
//
//            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            md.update(salt);
//            System.out.println(md.digest(salt));
//
//            byte[] hashPassword = md.digest(request.getPassword().getBytes(StandardCharsets.UTF_8));
//            System.out.println(hashPassword);
//
////            System.out.println("salt ========"+ Integer.toHexString(Integer.parseInt(request.getPassword())));
////            Integer.toHexString(Integer.parseInt(request.getPassword()));

//        studentModel.setPassword_salt(salt.toString());
//        studentModel.setPassword(securePassword);



        repository.persist(studentModel);

        generalResponse.setMessage("Register Success");
        generalResponse.setStatus(true);
        generalResponse.seterrorCode(0);
        return  generalResponse;
    }

//    private byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
//    {
//        //Always use a SecureRandom generator
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
//        //Create array for salt
//        byte[] salt = new byte[16];
//        //Get a random salt
//        sr.nextBytes(salt);
//        //return salt
//        return salt;
//    }

//    private static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt)
//    {
//        String generatedPassword = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            md.update(salt);
//            byte[] bytes = md.digest(passwordToHash.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for(int i=0; i< bytes.length ;i++)
//            {
//                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//            }
//            generatedPassword = sb.toString();
//        }
//        catch (NoSuchAlgorithmException e)
//        {
//            e.printStackTrace();
//        }
//        return generatedPassword;
//    }


}
