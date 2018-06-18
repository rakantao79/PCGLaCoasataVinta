package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas;

public class DataDetailedReport {

    public String vName;
    public String vType;
    public String vOrigin;
    public String vDestination;
    public String vETA;
    public String vETD;
    public String vInvestigator;
    public String vTimeStamp;


    public DataDetailedReport(){

    }

    public DataDetailedReport(String vName,String vType,String vOrigin, String vDestination, String vETA, String vETD, String vInvestigator, String vTimeStamp){
        this.vName = vName;
        this.vType = vType;
        this.vOrigin = vOrigin;
        this.vDestination = vDestination;
        this.vETA = vETA;
        this.vETD = vETD;
        this.vInvestigator = vInvestigator;
        this.vTimeStamp = vTimeStamp;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public String getvOrigin() {
        return vOrigin;
    }

    public void setvOrigin(String vOrigin) {
        this.vOrigin = vOrigin;
    }

    public String getvDestination() {
        return vDestination;
    }

    public void setvDestination(String vDestination) {
        this.vDestination = vDestination;
    }

    public String getvETA() {
        return vETA;
    }

    public void setvETA(String vETA) {
        this.vETA = vETA;
    }

    public String getvETD() {
        return vETD;
    }

    public void setvETD(String vETD) {
        this.vETD = vETD;
    }

    public String getvInvestigator() {
        return vInvestigator;
    }

    public void setvInvestigator(String vInvestigator) {
        this.vInvestigator = vInvestigator;
    }

    public String getvTimeStamp() {
        return vTimeStamp;
    }

    public void setvTimeStamp(String vTimeStamp) {
        this.vTimeStamp = vTimeStamp;
    }
}
