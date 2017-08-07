package uk.co.donnellyit.travelappjava.ws;

/**
 * Created by chrisdonnelly on 26/07/2017.
 */

public class Service {
    private String category;
    private String mode;
    private String service;
    private String train_uid;
    private String platform;
    private String operator;
    private String operator_name;
    private String aimed_departure_time;
    private String aimed_arrival_time;
    private String aimed_pass_time;
    private String origin_name;
    private String source;
    private String destination_name;
    private String status;
    private String expected_arrival_time;
    private String expected_departure_time;
    private String best_arrival_estimate_mins;
    private String best_departure_estimate_mins;

    public String getCategory() {
        return category;
    }

    public String getMode() {
        return mode;
    }

    public String getService() {
        return service;
    }

    public String getTrain_uid() {
        return train_uid;
    }

    public String getPlatform() {
        return platform;
    }

    public String getOperator() {
        return operator;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public String getAimed_departure_time() {
        return aimed_departure_time;
    }

    public String getAimed_arrival_time() {
        return aimed_arrival_time;
    }

    public String getAimed_pass_time() {
        return aimed_pass_time;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public String getSource() {
        return source;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public String getStatus() {
        return status;
    }

    public String getExpected_arrival_time() {
        return expected_arrival_time;
    }

    public String getExpected_departure_time() {
        return expected_departure_time;
    }

    public String getBest_arrival_estimate_mins() {
        return best_arrival_estimate_mins;
    }

    public String getBest_departure_estimate_mins() {
        return best_departure_estimate_mins;
    }
}
