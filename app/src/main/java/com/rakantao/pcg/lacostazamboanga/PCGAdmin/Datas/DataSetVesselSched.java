package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas;

public class DataSetVesselSched {

    private String day;
    private String locations;
    private String times;
    private String decision;
    private String Key;
    private String VesselName;

    public DataSetVesselSched() {

    }

    public DataSetVesselSched(String day, String locations, String times, String decision, String Key,String VesselName) {
        this.day = day;
        this.locations = locations;
        this.times = times;
        this.decision = decision;
        this.Key = Key;
        this.VesselName = VesselName;
    }

    public String getVesselName() {
        return VesselName;
    }

    public void setVesselName(String vesselName) {
        VesselName = vesselName;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLocation() {
        return locations;
    }

    public void setLocation(String location) {
        this.locations = location;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
