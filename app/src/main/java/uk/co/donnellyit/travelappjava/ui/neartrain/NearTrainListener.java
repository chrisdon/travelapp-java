package uk.co.donnellyit.travelappjava.ui.neartrain;

/**
 * Created by chrisdonnelly on 03/07/2017.
 */

public interface NearTrainListener extends LocationTracker.LocationUpdateListener {
    void onError();
}
