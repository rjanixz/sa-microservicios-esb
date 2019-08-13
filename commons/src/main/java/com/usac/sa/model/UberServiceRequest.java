package com.usac.sa.model;

public class UberServiceRequest {

    private String userId;
    private String from;
    private String to;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    /*
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ServiceRequest) {
            return this.id.equals(((ServiceRequest) obj).id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }*/
}
