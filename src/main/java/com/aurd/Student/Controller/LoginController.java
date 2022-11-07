package com.aurd.Student.Controller;

import com.aurd.Student.Constant.ErrorCode;
import com.aurd.Student.Constant.ErrorDescription;
import com.aurd.Student.Model.Request.LoginRequest;
import com.aurd.Student.Model.Response.LoginResponse;
import com.aurd.Student.service.UserService;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")

public class LoginController {

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse loginStudent(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        if (request.getContact() == null || request.getContact().isEmpty() || request.getPassword() == null || request.getPassword().isEmpty() || request.getInstId() == null) {
            response.setErrorCode(ErrorCode.ERROR_CODE_INVALID_REQUEST);
            response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_INVALID_REQUEST);
            response.setStatus(false);
            return response;
        }
        return userService.studentLogin(request, response);
    }
}
