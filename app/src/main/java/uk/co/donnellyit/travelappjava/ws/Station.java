package uk.co.donnellyit.travelappjava.ws;

/**
 * Created by chrisdonnelly on 26/07/2017.
 */

public class Station {
    private String name;
    private String crs;

    public Station(String name, String crs) {
        this.name = name;
        this.crs = crs;
    }

    public String getName() {
        return name;
    }

    public String getCrs() {
        return crs;
    }

}
