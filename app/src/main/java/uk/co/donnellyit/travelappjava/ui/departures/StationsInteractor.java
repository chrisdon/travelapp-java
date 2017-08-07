package uk.co.donnellyit.travelappjava.ui.departures;

import java.util.List;

import rx.Observable;
import uk.co.donnellyit.travelappjava.ws.Station;

/**
 * Created by chrisdonnelly on 27/07/2017.
 */

public interface StationsInteractor {
    Observable<List<Station>> fetchStations();
}
