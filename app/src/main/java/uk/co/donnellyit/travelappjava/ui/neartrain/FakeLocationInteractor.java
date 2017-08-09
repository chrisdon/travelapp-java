package uk.co.donnellyit.travelappjava.ui.neartrain;

import android.content.Context;
import android.location.Location;

import java.util.Calendar;

import uk.co.donnellyit.travelappjava.R;

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
        double latitude = Double.parseDouble(context.getString(R.string.fake_lat));
        newLoc.setLatitude(latitude);
        double longitude = Double.parseDouble(context.getString(R.string.fake_long));
        newLoc.setLongitude(longitude);
        listener.onUpdate(oldLoc, oldTime, newLoc, newTime);
    }
}
