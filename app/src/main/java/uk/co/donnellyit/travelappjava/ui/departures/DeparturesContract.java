package uk.co.donnellyit.travelappjava.ui.departures;

import java.util.List;

import uk.co.donnellyit.travelappjava.ui.common.TAContract;
import uk.co.donnellyit.travelappjava.ws.Station;
import uk.co.donnellyit.travelappjava.ws.TrainLiveResponse;

/**
 * Created by chrisdonnelly on 26/07/2017.
 */

public interface DeparturesContract {
    interface View extends TAContract.View {
        void displayLiveDepartures(TrainLiveResponse response);
        void displayStations(List<Station> stations);
        void displayError(String message);
        void showProgress();
        void hideProgress();
    }

    interface Presenter extends TAContract.Presenter<View> {
        void requestDepartures(String stationCode);
        void requestStations();
    }
}
