package com.aurd.Student.Controller;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static com.aurd.Service.s3;

@Path("/videos")
public class GetVideosController {

    @Path("/{id}")
    @GET
    @Produces({"video/mp4"})
    public Response getVideos(@PathParam("id") String videoId) throws IOException {
        System.out.println(videoId);
        final String bucketName = "educationapp";
        byte[] data = s3.getObject(bucketName,videoId).getObjectContent().readAllBytes();

        return Response.ok(data).build();
    }



}
