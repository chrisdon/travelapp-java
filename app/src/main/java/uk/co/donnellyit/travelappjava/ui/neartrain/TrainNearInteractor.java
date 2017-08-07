package uk.co.donnellyit.travelappjava.ui.neartrain;

import rx.Observable;
import uk.co.donnellyit.travelappjava.ws.TrainNearResponse;

/**
 * Created by chrisdonnelly on 03/07/2017.
 */

public interface TrainNearInteractor {
    Observable<TrainNearResponse> getResponse(double latitude, double longitude, int page, int rpp,
                                              String appId, String apiKey);
}
