package uk.co.donnellyit.travelappjava.ws;

/**
 * Created by chrisdonnelly on 30/06/2017.
 */

public class StationJson {
    private String station_code;
    private String atcocode;
    private String tiploc_code;
    private String name;
    private String mode;
    private String longitude;
    private String latitude;
    private String distance;

    public String getStation_code() {
        return station_code;
    }

    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }

    public String getAtcocode() {
        return atcocode;
    }

    public void setAtcocode(String atcocode) {
        this.atcocode = atcocode;
    }

    public String getTiploc_code() {
        return tiploc_code;
    }

    public void setTiploc_code(String tiploc_code) {
        this.tiploc_code = tiploc_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
