package com.aurd.Student.Model.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "purchase_books")
public class PurchaseBook {
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    private Integer booksid;
    private Integer stud_id;
    private String phone;
    private Long purchase_amount;
    private Long total_amount;
    private Long discounted_amount;
    private String method;
    private String details;
    private String address;
    private String delivery_status;
    private String trans_id;
    private Timestamp timestamp;


    public Integer getBooksid() {
        return booksid;
    }

    public void setBooksid(Integer booksid) {
        this.booksid = booksid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
