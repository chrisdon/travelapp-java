package uk.co.donnellyit.travelappjava.ui.neartrain;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.donnellyit.travelappjava.ws.TrainNearResponse;
import uk.co.donnellyit.travelappjava.ws.WsJavaUtil;

/**
 * Created by chrisdonnelly on 03/07/2017.
 */

public class TrainNearInteractorImpl implements TrainNearInteractor {
    @Override
    public Observable<TrainNearResponse> getResponse(double latitude, double longitude, int page, int rpp, String appId, String apiKey) {
        return WsJavaUtil.getService().getNearDepartures(latitude, longitude,
                1, 25, appId, apiKey).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
