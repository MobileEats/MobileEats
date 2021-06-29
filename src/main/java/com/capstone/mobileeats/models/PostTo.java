package com.capstone.mobileeats.models;

public class PostTo {
    private Boolean open;
    private String location;

    public PostTo(){};

    public PostTo(Boolean open, String location) {
        this.open = open;
        this.location = location;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
