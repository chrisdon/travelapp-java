package uk.co.donnellyit.travelappjava.ws;

import java.util.List;

/**
 * Created by chrisdonnelly on 30/06/2017.
 */

public class TrainNearResponse {
    private String minlon;
    private String minlat;
    private String maxlon;
    private String maxlat;
    private String searchlon;
    private String searchlat;
    private String page;
    private String rpp;
    private String total;
    private String request_time;
    private List<StationJson> stations;

    public String getMinlon() {
        return minlon;
    }

    public void setMinlon(String minlon) {
        this.minlon = minlon;
    }

    public String getMinlat() {
        return minlat;
    }

    public void setMinlat(String minlat) {
        this.minlat = minlat;
    }

    public String getMaxlon() {
        return maxlon;
    }

    public void setMaxlon(String maxlon) {
        this.maxlon = maxlon;
    }

    public String getMaxlat() {
        return maxlat;
    }

    public void setMaxlat(String maxlat) {
        this.maxlat = maxlat;
    }

    public String getSearchlon() {
        return searchlon;
    }

    public void setSearchlon(String searchlon) {
        this.searchlon = searchlon;
    }

    public String getSearchlat() {
        return searchlat;
    }

    public void setSearchlat(String searchlat) {
        this.searchlat = searchlat;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRpp() {
        return rpp;
    }

    public void setRpp(String rpp) {
        this.rpp = rpp;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRequest_time() {
        return request_time;
    }

    public void setRequest_time(String request_time) {
        this.request_time = request_time;
    }

    public List<StationJson> getStations() {
        return stations;
    }

    public void setStations(List<StationJson> stations) {
        this.stations = stations;
    }
}
