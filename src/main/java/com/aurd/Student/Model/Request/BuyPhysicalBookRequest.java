package com.aurd.Student.Model.Request;

public class BuyPhysicalBookRequest {

    private Integer bookId;
    private Integer studId;
    private String phone;
    private Long purchase_amount;
    private String method;
    private String transactionId;
    private String address;

    public Long getPurchase_amount() {
        return purchase_amount;
    }

    public void setPurchase_amount(Long purchase_amount) {
        this.purchase_amount = purchase_amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
