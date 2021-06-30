package com.capstone.mobileeats.models;

public class PostTo {
    private Boolean open;
    private String location;
    private Long id;

    public PostTo(){};

    public PostTo(Boolean open, String location, Long id) {
        this.open = open;
        this.location = location;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
