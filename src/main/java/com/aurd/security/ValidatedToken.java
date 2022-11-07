package com.aurd.security;


import com.aurd.Student.Model.Entity.Session;

public class ValidatedToken {

    private boolean valid;
    private Integer validInvalidCode;
    private String validDescription;
    private boolean userAllowed;
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    //    private User user;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Integer getValidInvalidCode() {
        return validInvalidCode;
    }

    public void setValidInvalidCode(Integer validInvalidCode) {
        this.validInvalidCode = validInvalidCode;
    }

    public String getValidDescription() {
        return validDescription;
    }

    public void setValidDescription(String validDescription) {
        this.validDescription = validDescription;
    }

    public boolean isUserAllowed() {
        return userAllowed;
    }

    public void setUserAllowed(boolean userAllowed) {
        this.userAllowed = userAllowed;
    }


}
