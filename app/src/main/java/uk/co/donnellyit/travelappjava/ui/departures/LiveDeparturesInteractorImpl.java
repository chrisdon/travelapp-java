package uk.co.donnellyit.travelappjava.ui.departures;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.donnellyit.travelappjava.ws.TrainLiveResponse;
import uk.co.donnellyit.travelappjava.ws.WsJavaUtil;

/**
 * Created by chrisdonnelly on 26/07/2017.
 */

public class LiveDeparturesInteractorImpl implements LiveDeparturesInteractor{
    @Override
    public Observable<TrainLiveResponse> requestLiveDepartures(String station_code, String apiKey, String appId) {
        return WsJavaUtil.getService().getLiveDepartures(station_code, appId, apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
