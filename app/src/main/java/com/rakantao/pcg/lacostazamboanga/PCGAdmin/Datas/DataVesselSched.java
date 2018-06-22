package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas;

public class DataVesselSched {

    private String VesselType;
    private String VesselName;
    private String Origin;
    private String DepartureTime;
    private String Destination;
    private String ArrivalTime;
    private String ScheduleDay;
    private String ActualDepartedTime;
    private String Key;




    public DataVesselSched(){

    }

    public DataVesselSched(String VesselType, String VesselName, String ScheduleDay, String Origin, String DepartureTime,
                            String Destination,String ArrivalTime,String ActualDepartedTime, String Key){

        this.VesselType = VesselType;
        this.VesselName = VesselName;
        this.ScheduleDay = ScheduleDay;
        this.Origin = Origin;
        this.DepartureTime = DepartureTime;
        this.Destination = Destination;
        this.ArrivalTime = ArrivalTime;
        this.ActualDepartedTime = ActualDepartedTime;
        this.Key = Key;

    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getVesselType() {
        return VesselType;
    }

    public String getActualDepartedTime() {
        return ActualDepartedTime;
    }

    public void setActualDepartedTime(String actualDepartedTime) {
        ActualDepartedTime = actualDepartedTime;
    }

    public void setVesselType(String vesselType) {
        VesselType = vesselType;
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
