package com.aurd.Student.Controller;

import com.aurd.Student.Model.Request.StudentRequest;
import com.aurd.Student.Model.Response.StudentPostsResponse;
import com.aurd.Student.Repository.StudentsRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/updateStudent")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentController {
    @Inject
    StudentsRepository studentsRepository;

    @POST
    @Transactional
    public StudentPostsResponse updateStudentPost(StudentRequest request) {
        StudentPostsResponse responce = new StudentPostsResponse();
        studentsRepository.updateStudent(request);
        responce.setMessage("Student  Update");
        responce.setStatus(true);
        responce.setErrorCode(0);
        return responce;

    }

}
