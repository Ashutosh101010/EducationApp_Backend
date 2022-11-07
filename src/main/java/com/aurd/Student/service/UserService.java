package com.aurd.Student.service;

import com.aurd.Student.Constant.Enums;
import com.aurd.Student.Constant.ErrorCode;
import com.aurd.Student.Constant.ErrorDescription;
import com.aurd.Student.Model.Entity.Session;
import com.aurd.Student.Model.Entity.Student;
import com.aurd.Student.Model.Entity.StudentNotes;
import com.aurd.Student.Model.Request.AddNotesRequest;
import com.aurd.Student.Model.Request.LoginRequest;
import com.aurd.Student.Model.Request.SaveUnsaveBookMarkRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Model.Response.LoginResponse;
import com.aurd.Student.Model.Response.SaveUnsaveBookmarkResponse;
import com.aurd.Student.Repository.BookMarkRepository;
import com.aurd.Student.Repository.SessionRepository;
import com.aurd.Student.Repository.StudentNotesRepository;
import com.aurd.Student.Repository.StudentRepository;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserService {

    @Inject
    StudentRepository studentRepository;

    @Inject
    SessionRepository sessionRepository;

    @Inject
    BookMarkRepository bookMarkRepository;

    @Inject
    StudentNotesRepository studentNotesRepository;
    public LoginResponse studentLogin(LoginRequest request,LoginResponse response)
    {
        Student student=studentRepository.fetchStudent(request.getContact(),request.getInstId());
        if(student==null)
        {
            response.setErrorCode(ErrorCode.ERROR_CODE_USER_NOT_FOUND);
            response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_USER_NOT_FOUND);
            response.setStatus(false);
            return response;
        }
        else if(student.getPassword().equals(request.getPassword()))
        {

            Session session=sessionRepository.createSession(student.getId(),Enums.userTpe.student.name());
            response.setStudent(student);
            response.setStatus(true);
            response.setAuthToken(session.getToken());
            response.setErrorCode(ErrorCode.ERROR_CODE_SUCCESS);
            response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_SUCCESS);
            return response;

        }else{
            response.setStatus(false);
            response.setErrorCode(ErrorCode.ERROR_CODE_CREDENTIALS_MISMATCH);
            response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_CREDENTIALS_MISMATCH);
            return response;
        }
    }


    public SaveUnsaveBookmarkResponse saveUnsaveBookmark(SaveUnsaveBookMarkRequest request,SaveUnsaveBookmarkResponse response)
    {
        Integer operation=bookMarkRepository.saveUnsaveBookmark(request);
        response.setOperation(operation);
        response.setStatus(true);
        response.setErrorDescription(ErrorDescription.ERROR_DESCRIPTION_SUCCESS);
        response.setErrorCode(ErrorCode.ERROR_CODE_SUCCESS);
        return response;
    }

    public GeneralResponse addNote(AddNotesRequest request)
    {
        studentNotesRepository.
    }
}
