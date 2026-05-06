package com.example.demo.dto;

import java.io.Serializable;

public class HealthDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private String uptime;
    private String version;

    public HealthDto() {}

    public HealthDto(String status, String uptime, String version) {
        this.status = status;
        this.uptime = uptime;
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}