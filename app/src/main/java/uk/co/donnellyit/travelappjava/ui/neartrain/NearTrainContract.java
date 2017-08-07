package uk.co.donnellyit.travelappjava.ui.neartrain;

import uk.co.donnellyit.travelappjava.ui.common.TAContract;
import uk.co.donnellyit.travelappjava.ws.TrainNearResponse;

/**
 * Created by chrisdonnelly on 30/06/2017.
 */

public interface NearTrainContract {
    interface View extends TAContract.View {
        void showProgress();
        void hideProgress();
        void displayNearestStations(TrainNearResponse response);
        void onError(String msg);
    }

    interface Presenter extends TAContract.Presenter<View> {
        void getNearStations(int pageNumber);
    }
}
