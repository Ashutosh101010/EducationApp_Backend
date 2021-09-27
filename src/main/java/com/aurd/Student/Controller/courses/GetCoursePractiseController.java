package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.Request.testseries.Get_PractiseTestSeries_Request;
import com.aurd.Student.Model.Response.GetCourse_PractiseTest_Response;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/courses/getPractiseTest")
public class GetCoursePractiseController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public GetCourse_PractiseTest_Response getPractiseTest(Get_PractiseTestSeries_Request request){

        GetCourse_PractiseTest_Response response= new GetCourse_PractiseTest_Response() ;

   return response;}

}
