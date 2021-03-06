package com.aurd.Student.Model.Request;

public class DeviceTokenRequest {
     private long userId;
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "DeviceTokenRequest{" +
                "userId=" + userId +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
