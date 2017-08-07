package uk.co.donnellyit.travelappjava.ui.neartrain;

import android.content.Context;
import android.location.Location;
import android.util.Log;

/**
 * Created by chrisdonnelly on 30/06/2017.
 */

public class LocationInteractorImpl implements LocationInteractor, LocationTracker.LocationUpdateListener {
    private final static String LOGTAG = LocationInteractor.class.getSimpleName();
    private FallbackLocationTracker mTracker;
    private NearTrainListener mListener;
    @Override
    public void getLocation(Context context, NearTrainListener listener) {
        mListener = listener;
        mTracker = new FallbackLocationTracker(context);
        mTracker.start(this);
        Location location = mTracker.getLocation();
        if(location != null)
        Log.d(LOGTAG, "current location: "+location.toString());
}

    @Override
    public void onUpdate(Location oldLoc, long oldTime, Location newLoc, long newTime) {
        if(oldLoc != null) {
            Log.d(LOGTAG, "old location: "+oldLoc.toString());
        }
        if(newLoc != null) {
            Log.d(LOGTAG, "new location: "+newLoc.toString());
            mTracker.stop();
            mListener.onUpdate(oldLoc, oldTime, newLoc, newTime);

        } else {
            mListener.onError();
        }
    }
}
