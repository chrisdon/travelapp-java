package uk.co.donnellyit.travelappjava.ui.neartrain;

import android.content.Context;

/**
 * Created by chrisdonnelly on 30/06/2017.
 */

public interface LocationInteractor {
    void getLocation(Context context, NearTrainListener listener);
}
