package com.aurd.Student.Model.Request;

import java.sql.Date;

public class RegisterRequest {
    String fname;
    String password;
    String email;
    String contact;
    int inst_id;
    String reg_status;

    public String getReg_status() {
        return reg_status;
    }

    public void setReg_status(String reg_status) {
        this.reg_status = reg_status;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

      public int getInst_id() {
        return inst_id;
      }

      public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
      }


}
