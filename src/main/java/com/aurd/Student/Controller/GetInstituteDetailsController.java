package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.GetInstituteDetailEntity;
import com.aurd.Student.Model.Request.GetInstituteDetailsRequest;
import com.aurd.Student.Model.Response.GetInstituteDetailsResponse;

import com.aurd.Student.Repository.GetInstituteDetailsRepository;


import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("/getInstituteDetails")

public class GetInstituteDetailsController {

    @Inject
    GetInstituteDetailsRepository repository;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    @Transactional

    public GetInstituteDetailsResponse getDetails(GetInstituteDetailsRequest request) {


       ArrayList<GetInstituteDetailEntity> DetailsList = new ArrayList();


        String string = "SELECT institute_master.id, institute_master.institute,institute_master.address,institute_master.city_id," +
                "institute_master.state_id,institute_master.email, institute_master.contact," +
                "institute_master.logo,institute_master.library,institute_master.libraryFee,institute_master.hostelFee," +
                "institute_master.domain, institute_data.quote,institute_data.clip,institute_data.facebook,institute_data.instagram," +
                "institute_data.course,institute_data.about,institute_data.offer, institute_data.awards " +
                "FROM institute_master INNER JOIN institute_data ON institute_master.id=institute_data.id WHERE institute_master.id = ?";

        Query query = repository.getEntityManager().createNativeQuery(string);
        query.setParameter(1, request.getInst_id());

        ArrayList<Object[]> list = (ArrayList<Object[]>) query.getResultList();
        list.forEach(objects -> {
            GetInstituteDetailEntity getInstituteDetailEntity = new GetInstituteDetailEntity();
            getInstituteDetailEntity.setId(Integer.parseInt(objects[0].toString()));
            getInstituteDetailEntity.setInstitute(objects[1].toString());
            getInstituteDetailEntity.setAddress(objects[2].toString());
            getInstituteDetailEntity.setCity(Integer.parseInt(objects[3].toString()));
            getInstituteDetailEntity.setState(Integer.parseInt(objects[4].toString()));
            getInstituteDetailEntity.setEmail(objects[5].toString());
            getInstituteDetailEntity.setContact(objects[6].toString());
            getInstituteDetailEntity.setLogo(objects[7].toString());
            getInstituteDetailEntity.setLibrary(objects[8].toString());
            getInstituteDetailEntity.setLibraryFee(Float.parseFloat(objects[9].toString()));
            getInstituteDetailEntity.setHostelFee(Float.parseFloat(objects[10].toString()));
            getInstituteDetailEntity.setDomain(objects[11].toString());
            getInstituteDetailEntity.setQuote(objects[12].toString());
            getInstituteDetailEntity.setClip(objects[13].toString());
            getInstituteDetailEntity.setFacebook(objects[14].toString());
            getInstituteDetailEntity.setInstagram(objects[15].toString());
            getInstituteDetailEntity.setCourse(objects[16].toString());
            getInstituteDetailEntity.setAbout(objects[17].toString());
            getInstituteDetailEntity.setOffer(objects[18].toString());
            getInstituteDetailEntity.setAwards(objects[19].toString());


            DetailsList.add(getInstituteDetailEntity);

        });
        GetInstituteDetailsResponse getInstituteDetailsResponse = new GetInstituteDetailsResponse();
        getInstituteDetailsResponse.setDetails(DetailsList);
        getInstituteDetailsResponse.setMessage("Get Comment Successfully");
        getInstituteDetailsResponse.setStatus(true);
        getInstituteDetailsResponse.setErrorCode(0);

        return getInstituteDetailsResponse;

    }
}
