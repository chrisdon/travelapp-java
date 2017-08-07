package uk.co.donnellyit.travelappjava.ui.neartrain;

import android.location.Location;

/**
 * Created by chrisdonnelly on 30/06/2017.
 */

public interface LocationTracker {
    interface LocationUpdateListener{
        void onUpdate(Location oldLoc, long oldTime, Location newLoc, long newTime);
    }

    void start();
    void start(LocationUpdateListener update);

    void stop();

    boolean hasLocation();

    boolean hasPossiblyStaleLocation();

    Location getLocation();

    Location getPossiblyStaleLocation();
}
