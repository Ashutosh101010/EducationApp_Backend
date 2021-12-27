package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.Banners;
import com.aurd.Student.Model.Response.LatestUpdateResponse;
import com.aurd.Student.Repository.BannerImageRepository;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;

import static com.aurd.Service.s3;

@Path("/getBanners")
public class BannerController {
    @Inject
    BannerImageRepository imageRepository;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)


    public LatestUpdateResponse getImage(@PathParam("id") String instID) throws IOException {
        ArrayList<Banners> bannerList = (ArrayList<Banners>)
                imageRepository.list("inst_id=?1 and status=?2",Long.parseLong(instID),1);

        LatestUpdateResponse response = new LatestUpdateResponse();
        response.setImageList(bannerList);
        response.setStatus(true);
        response.setErrorCode(0);
        return  response;
    }
}
