package com.aurd.Student.Controller.bucketController;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.aurd.Service.s3;


@Path("/video")
public class VideoController {



    @Path("/{id}")
    @GET
    @Produces({"video/mp4"})
    public Response getImage(@PathParam("id") String videoId) throws IOException {

        System.out.println(videoId);
        final String bucketName = "educationapp";

//        listKeysInDirectory(bucketName,"video/");


        S3Object object =  s3.getObject(new GetObjectRequest(bucketName,"video/"+videoId));
        byte[] data = object.getObjectContent().readAllBytes();

        System.out.println(data);


        return  Response.ok(data).build();
    }

}
