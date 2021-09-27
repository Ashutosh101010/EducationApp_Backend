package com.aurd.Student.Controller.bucketController;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import static com.aurd.Service.s3;

@Path("/image")
public class ImageController {

    @Path("/{id}")
    @GET
    @Produces({"image/png", "image/jpg", "image/gif"})
    public Response getImage(@PathParam("id") String imageId) throws IOException {

        System.out.println(imageId);
        final String bucketName = "educationapp";


        byte[] data = s3.getObject(bucketName,imageId).getObjectContent().readAllBytes();

        return Response.ok(data).build();
    }

}