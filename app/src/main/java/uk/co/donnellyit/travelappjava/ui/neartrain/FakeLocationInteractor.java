package uk.co.donnellyit.travelappjava.ui.neartrain;

import android.content.Context;
import android.location.Location;

import java.util.Calendar;

/**
 * Created by chrisdonnelly on 13/07/2017.
 */

public class FakeLocationInteractor implements LocationInteractor {
    @Override
    public void getLocation(Context context, NearTrainListener listener) {
        Location oldLoc = new Location("Fake");
        long oldTime = Calendar.getInstance().getTimeInMillis();
        Location newLoc = oldLoc;
        long newTime = Calendar.getInstance().getTimeInMillis();
        // lat=51.3562191&lon=-0.141687
        newLoc.setLatitude(51.3562191);
        newLoc.setLongitude(-0.141687);
        listener.onUpdate(oldLoc, oldTime, newLoc, newTime);
    }
}
