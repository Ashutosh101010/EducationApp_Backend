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
import java.util.Arrays;

@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterController {

    @Inject
    StudentRepository repository;
    @POST
    @Transactional
    public GeneralResponse register(RegisterRequest request)
             {
        GeneralResponse generalResponse = new GeneralResponse();
        Gson gson = new Gson();
        StudentModel studentModel = gson.fromJson(gson.toJson(request),StudentModel.class);



        studentModel.setPassword_salt(request.getPassword());

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
        generalResponse.setStatusCode(0);
        return  generalResponse;
    }

    private byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }

    private static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }


}
