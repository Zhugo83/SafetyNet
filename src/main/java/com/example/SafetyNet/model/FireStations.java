package com.example.SafetyNet.model;

public class FireStations {
    private String address;
    private String station;

    public void FireStation(){}
    public void FireStation(String address, String station){
        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getStation() {
        return station;
    }
    public void setStation(String station) {
        this.station = station;
    }
}
