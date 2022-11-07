package com.aurd.Student.Controller;

import com.aurd.Student.Constant.Enums;
import com.aurd.Student.Constant.ErrorCode;
import com.aurd.Student.Constant.ErrorDescription;
import com.aurd.Student.Model.Request.SaveUnsaveBookMarkRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Model.Response.SaveUnsaveBookmarkResponse;
import com.aurd.Student.Repository.BookMarkRepository;
import com.aurd.Student.Utility;
import com.aurd.Student.service.UserService;
import com.aurd.security.Security;
import com.aurd.security.ValidatedToken;
import org.jboss.resteasy.reactive.RestHeader;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;


@Path("/student/saveUnsaveBookmark")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class saveUnsaveBookmarkController {


    @Inject
    Security security;

    @Inject
    UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SaveUnsaveBookmarkResponse saveUnsaveBookmark(@RestHeader String token, SaveUnsaveBookMarkRequest request){


        SaveUnsaveBookmarkResponse response=new SaveUnsaveBookmarkResponse();

        ArrayList<String> rolesAllowed=new ArrayList<>();
        rolesAllowed.add(Enums.userTpe.student.name());

        if(token==null || token.isEmpty())
        {
            response.setErrorCode(ErrorCode.ERROR_CODE_UNAUTHORIZED_ACCESS);
            response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_UNAUTHORIZED_ACCESS);
            response.setStatus(false);
            return response;
        }


        if(request.getPostId()==null || request.getAddedBy()==null || request.getOperation()==null || request.getType()==null || request.getType().isEmpty())
        {
            response.setErrorCode(ErrorCode.ERROR_CODE_INVALID_REQUEST);
            response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_INVALID_REQUEST);
            response.setStatus(false);
            return response;
        }


        if(!Utility.isValidEntityEnum(request.getType()))
        {
            response.setErrorCode(ErrorCode.ERROR_CODE_INVALID_REQUEST);
            response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_INVALID_REQUEST);
            response.setStatus(false);
            return response;
        }

        ValidatedToken validatedToken= security.validateToken(token,rolesAllowed);
        if(!validatedToken.isValid())
        {
            response.setStatus(false);
            response.setErrorCode(validatedToken.getValidInvalidCode());
            response.setErrorDescription(validatedToken.getValidDescription());
            return response;
        }

        return userService.saveUnsaveBookmark(request,response);



    }

}
