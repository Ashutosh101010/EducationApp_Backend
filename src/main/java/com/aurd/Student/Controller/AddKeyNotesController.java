//package com.aurd.Student.Controller;
//
//
//import com.aurd.Student.Constant.Enums;
//import com.aurd.Student.Constant.ErrorCode;
//import com.aurd.Student.Constant.ErrorDescription;
//import com.aurd.Student.Model.Request.AddKeyNotes;
//import com.aurd.Student.Model.Request.AddNotesRequest;
//import com.aurd.Student.Model.Response.GeneralResponse;
//import com.aurd.Student.Repository.AddKeyNotesRepository;
//import com.aurd.Student.Repository.StudentNotesRepository;
//import com.aurd.Student.Utility;
//import com.aurd.Student.service.UserService;
//import com.aurd.security.Security;
//import com.aurd.security.ValidatedToken;
//import org.jboss.resteasy.reactive.RestHeader;
//
//import javax.inject.Inject;
//import javax.transaction.Transactional;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.util.ArrayList;
//
//@Path("/addKeyNotes")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//
//public class AddKeyNotesController {
//    @Inject
//    Security security;
//
//    @Inject
//    UserService userService;
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public GeneralResponse addKeyNotes(@RestHeader String token, AddKeyNotes request) {
//
//        GeneralResponse response=new GeneralResponse();
//
//
//        ArrayList<String> rolesAllowed=new ArrayList<>();
//        rolesAllowed.add(Enums.userTpe.student.name());
//
//        if(token==null || token.isEmpty())
//        {
//            response.setErrorCode(ErrorCode.ERROR_CODE_UNAUTHORIZED_ACCESS);
//            response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_UNAUTHORIZED_ACCESS);
//            response.setStatus(false);
//            return response;
//        }
//
//        //////////
//        if(request.getPostId()==null || request.getAddedBy()==null || request.getOperation()==null || request.getType()==null || request.getType().isEmpty())
//        {
//            response.setErrorCode(ErrorCode.ERROR_CODE_INVALID_REQUEST);
//            response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_INVALID_REQUEST);
//            response.setStatus(false);
//            return response;
//        }
//
/////////
//        ValidatedToken validatedToken= security.validateToken(token,rolesAllowed);
//        if(!validatedToken.isValid())
//        {
//            response.setStatus(false);
//            response.setErrorCode(validatedToken.getValidInvalidCode());
//            response.setErrorDescription(validatedToken.getValidDescription());
//            return response;
//        }
//
//
//
//    }
//}