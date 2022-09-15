package com.aurd.Student.Model.Request;

public class PurchaseAudioBookRequest {
    private Integer audioBookId;
    private Integer studId;
    private String phone;
    private Long purchase_amount;
    private String method;
    private String transactionId;

    public Integer getAudioBookId() {
        return audioBookId;
    }

    public void setAudioBookId(Integer audioBookId) {
        this.audioBookId = audioBookId;
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

    public Long getPurchase_amount() {
        return purchase_amount;
    }

    public void setPurchase_amount(Long purchase_amount) {
        this.purchase_amount = purchase_amount;
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
