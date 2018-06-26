package com.rakantao.pcg.lacostazamboanga.PublicUser.Datas;

public class DataUserSchedList {

    private String ArrivalTime;
    private String DepartureTime;
    private String Destination;
    private String Origin;
    private String VesselName;

    public DataUserSchedList() {
    }

    public DataUserSchedList(String arrivalTime, String departureTime, String destination, String origin, String vesselName) {
        ArrivalTime = arrivalTime;
        DepartureTime = departureTime;
        Destination = destination;
        Origin = origin;
        VesselName = vesselName;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getVesselName() {
        return VesselName;
    }

    public void setVesselName(String vesselName) {
        VesselName = vesselName;
    }
}
