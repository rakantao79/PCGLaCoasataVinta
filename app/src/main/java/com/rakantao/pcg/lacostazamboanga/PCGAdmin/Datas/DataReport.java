package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas;

public class DataReport {

    public String ReportDescription;
    public String ReportTimeStamp;

    public DataReport(){

    }

    public DataReport(String ReportDescription, String ReportTimeStamp){
        this.ReportDescription = ReportDescription;
        this.ReportTimeStamp = ReportTimeStamp;
    }

    public String getReportDescription() {
        return ReportDescription;
    }

    public void setReportDescription(String reportDescription) {
        ReportDescription = reportDescription;
    }

    public String getReportTimeStamp() {
        return ReportTimeStamp;
    }

    public void setReportTimeStamp(String reportTimeStamp) {
        ReportTimeStamp = reportTimeStamp;
    }
}
