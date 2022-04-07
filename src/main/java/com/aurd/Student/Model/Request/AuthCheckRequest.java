package com.aurd.Student.Model.Request;

public class AuthCheckRequest {

    Long user_id;
    String deviceId;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "AuthCheckRequest{" +
                "user_id=" + user_id +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
