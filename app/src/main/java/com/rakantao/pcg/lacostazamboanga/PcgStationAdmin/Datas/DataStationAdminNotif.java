package com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.Datas;

public class DataStationAdminNotif {

    public String DistressType;
    public String DistressDescription;
    public String DistressRemarks;
    public String Key;
    public String OriginStation;
    public String DestinationStation;
    public String NotifDate;
    public String NotifStatus;
    public String VesselName;
    public String NotificationType;

    public DataStationAdminNotif(){

    }

    public DataStationAdminNotif(String DistressType, String DistressDescription, String DistressRemarks, String Key, String OriginStation,
                                 String DestinationStation, String NotifDate, String NotifStatus, String VesselName, String NotificationType){
        this.DistressType = DistressType;
        this.DistressDescription = DistressDescription;
        this.DistressRemarks = DistressRemarks;
        this.Key = Key;
        this.OriginStation = OriginStation;
        this.DestinationStation = DestinationStation;
        this.NotifDate = NotifDate;
        this.NotifStatus = NotifStatus;
        this.VesselName = VesselName;
        this.NotificationType = NotificationType;
    }

    public String getDistressRemarks() {
        return DistressRemarks;
    }

    public void setDistressRemarks(String distressRemarks) {
        DistressRemarks = distressRemarks;
    }

    public String getNotificationType() {
        return NotificationType;
    }

    public void setNotificationType(String notificationType) {
        NotificationType = notificationType;
    }

    public String getDistressType() {
        return DistressType;
    }

    public void setDistressType(String distressType) {
        DistressType = distressType;
    }

    public String getDistressDescription() {
        return DistressDescription;
    }

    public void setDistressDescription(String distressDescription) {
        DistressDescription = distressDescription;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getOriginStation() {
        return OriginStation;
    }

    public void setOriginStation(String originStation) {
        OriginStation = originStation;
    }

    public String getDestinationStation() {
        return DestinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        DestinationStation = destinationStation;
    }

    public String getNotifDate() {
        return NotifDate;
    }

    public void setNotifDate(String notifDate) {
        NotifDate = notifDate;
    }

    public String getNotifStatus() {
        return NotifStatus;
    }

    public void setNotifStatus(String notifStatus) {
        NotifStatus = notifStatus;
    }

    public String getVesselName() {
        return VesselName;
    }

    public void setVesselName(String vesselName) {
        VesselName = vesselName;
    }
}
