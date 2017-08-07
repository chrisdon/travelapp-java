package uk.co.donnellyit.travelappjava.ui.departures;

import rx.Observable;
import uk.co.donnellyit.travelappjava.ws.TrainLiveResponse;

/**
 * Created by chrisdonnelly on 26/07/2017.
 */

public interface LiveDeparturesInteractor {
    Observable<TrainLiveResponse> requestLiveDepartures(String station_code, String apiKey, String appId);
}
