package com.rakantao.pcg.lacostazamboanga.PublicUser.Datas;

public class DataUserSchedList {

    private String VesselName;
    private String Origin;
    private String DepartureTime;
    private String Destination;
    private String ArrivalTime;
    private String ScheduleDay;

    public DataUserSchedList() {
    }

    public DataUserSchedList(String vesselName, String origin, String departureTime, String destination, String arrivalTime, String scheduleDay) {
        VesselName = vesselName;
        Origin = origin;
        DepartureTime = departureTime;
        Destination = destination;
        ArrivalTime = arrivalTime;
        ScheduleDay = scheduleDay;
    }

    public String getVesselName() {
        return VesselName;
    }

    public void setVesselName(String vesselName) {
        VesselName = vesselName;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
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

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getScheduleDay() {
        return ScheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        ScheduleDay = scheduleDay;
    }
}
