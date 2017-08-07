package uk.co.donnellyit.travelappjava.util;

import android.content.Context;

import uk.co.donnellyit.travelappjava.ui.departures.DeparturesPresenter;
import uk.co.donnellyit.travelappjava.ui.departures.LiveDeparturesInteractorImpl;
import uk.co.donnellyit.travelappjava.ui.departures.StationsInteractorImpl;
import uk.co.donnellyit.travelappjava.ui.neartrain.LocationInteractorImpl;
import uk.co.donnellyit.travelappjava.ui.neartrain.NearTrainPresenter;
import uk.co.donnellyit.travelappjava.ui.neartrain.TrainNearInteractorImpl;

/**
 * Created by chrisdonnelly on 13/07/2017.
 */

public class Injection {
    public static NearTrainPresenter injectNearTrainPresenter(Context context) {
        return new NearTrainPresenter(context, new LocationInteractorImpl(), new TrainNearInteractorImpl());
    }

    public static DeparturesPresenter injectDeparturesPresenter(Context context) {
        return new DeparturesPresenter(context, new LiveDeparturesInteractorImpl(), new StationsInteractorImpl(context));
    }
}
