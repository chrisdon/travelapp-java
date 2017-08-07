package uk.co.donnellyit.travelappjava.ws;

/**
 * Created by chrisdonnelly on 25/07/2017.
 */

public class TrainLiveResponse {
    private String date;
    private String time_of_day;
    private String request_time;
    private String station_name;
    private String station_code;
    private Departures departures;

    public String getDate() {
        return date;
    }

    public String getTime_of_day() {
        return time_of_day;
    }

    public String getRequest_time() {
        return request_time;
    }

    public String getStation_name() {
        return station_name;
    }

    public String getStation_code() {
        return station_code;
    }

    public Departures getDepartures() {
        return departures;
    }
}
