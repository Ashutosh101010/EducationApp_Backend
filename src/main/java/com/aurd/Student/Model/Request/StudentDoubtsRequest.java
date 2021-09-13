package com.aurd.Student.Model.Request;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.function.ToIntBiFunction;

import static javax.ws.rs.core.MediaType.*;

public class StudentDoubtsRequest {


    @FormParam("discription")
    @PartType(TEXT_PLAIN)
    private String discription;

    @FormParam("pic")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream pic;

    @FormParam("post_status")
    @PartType(TEXT_PLAIN)
    private int post_status;


    @FormParam("added_by")
    @PartType(TEXT_PLAIN)
   private int added_by;


    private Timestamp added_on;


    private Timestamp updated_on;

    @FormParam("inst_id")
    @PartType(TEXT_PLAIN)
     private int inst_id;

    @FormParam("post_approved_by")
    @PartType(TEXT_PLAIN)
    private int post_approved_by;


    private Timestamp  post_approved_on;



    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public InputStream getPic() {
        return pic;
    }

    public void setPic(InputStream pic) {
        this.pic = pic;
    }

    public int getPost_status() {
        return post_status;
    }

    public void setPost_status(int post_status) {
        this.post_status = post_status;
    }

    public int getAdded_by() {
        return added_by;
    }


    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Timestamp updated_on) {
        this.updated_on = updated_on;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getPost_approved_by() {
        return post_approved_by;
    }

    public void setPost_approved_by(int post_approved_by) {
        this.post_approved_by = post_approved_by;
    }

    public Timestamp getPost_approved_on() {
        return post_approved_on;
    }

    public void setPost_approved_on(Timestamp post_approved_on) {
        this.post_approved_on = post_approved_on;
    }
}
