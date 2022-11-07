package com.aurd.Student.Constant;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class ErrorCode {

    public static Integer ERROR_CODE_SUCCESS=0;

    public static Integer ERROR_CODE_INVALID_REQUEST=05;
    public static Integer ERROR_CODE_USER_NOT_FOUND=10;

    public static Integer ERROR_CODE_CREDENTIALS_MISMATCH=11;
    public static Integer ERROR_CODE_UNAUTHORIZED_ACCESS=401;


}
