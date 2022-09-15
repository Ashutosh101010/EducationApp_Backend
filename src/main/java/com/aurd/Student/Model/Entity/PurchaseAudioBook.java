package com.aurd.Student.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "purchase_audiobooks")
@Entity
public class PurchaseAudioBook {
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    private Integer audiobooksid;
    private Integer stud_id;
    private String phone;
    private Integer assigned_by;
    private Long purchase_amount;
    private Long total_amount;
    private Long discounted_amount;
    private Long discount;
    private String method;
    private String details;
    private String trans_id;
    private Timestamp created_on;

    public Integer getAudiobooksid() {
        return audiobooksid;
    }

    public void setAudiobooksid(Integer audiobooksid) {
        this.audiobooksid = audiobooksid;
    }

    public Integer getStud_id() {
        return stud_id;
    }

    public void setStud_id(Integer stud_id) {
        this.stud_id = stud_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAssigned_by() {
        return assigned_by;
    }

    public void setAssigned_by(Integer assigned_by) {
        this.assigned_by = assigned_by;
    }

    public Long getPurchase_amount() {
        return purchase_amount;
    }

    public void setPurchase_amount(Long purchase_amount) {
        this.purchase_amount = purchase_amount;
    }

    public Long getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Long total_amount) {
        this.total_amount = total_amount;
    }

    public Long getDiscounted_amount() {
        return discounted_amount;
    }

    public void setDiscounted_amount(Long discounted_amount) {
        this.discounted_amount = discounted_amount;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }
}
