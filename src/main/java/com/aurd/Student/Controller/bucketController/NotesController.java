package com.aurd.Student.Controller.bucketController;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static com.aurd.Service.s3;

@Path("/notes")
public class NotesController {
    @Path("/{id}")
    @GET
    @Produces({"application/pdf"})

    public Response getNotes(@PathParam("id") String notesId) throws IOException {
        final String bucketName = "educationapp";

        S3Object object =  s3.getObject(new GetObjectRequest(bucketName,"uploads/"+notesId));
        byte[] data = object.getObjectContent().readAllBytes();

        System.out.println(data);


        return  Response.ok(data).build();
    }
}
