package uk.co.donnellyit.travelappjava.ui.departures;

import android.content.Context;
import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import uk.co.donnellyit.travelappjava.R;
import uk.co.donnellyit.travelappjava.ui.common.TAPresenter;
import uk.co.donnellyit.travelappjava.ws.Station;
import uk.co.donnellyit.travelappjava.ws.TrainLiveResponse;

/**
 * Created by chrisdonnelly on 26/07/2017.
 */

public class DeparturesPresenter extends TAPresenter<DeparturesContract.View> implements
        DeparturesContract.Presenter {
    private final static String TAG = DeparturesPresenter.class.getSimpleName();
    private LiveDeparturesInteractor mDeparturesInteractor;
    private StationsInteractor mStationsInteractor;
    private Context mContext;

    public DeparturesPresenter(Context context, LiveDeparturesInteractor departuresInteractor,
                               StationsInteractor stationsInteractor) {
        mDeparturesInteractor = departuresInteractor;
        mStationsInteractor = stationsInteractor;
        mContext = context;
    }

    @Override
    public void requestDepartures(String stationCode) {
        String api_key  = mContext.getString(R.string.api_key);
        String app_id = mContext.getString(R.string.app_id);
        getView().showProgress();
        mDeparturesInteractor.requestLiveDepartures(stationCode, api_key, app_id)
                .subscribe(new Subscriber<TrainLiveResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().displayError(e.getMessage());
                        getView().hideProgress();
                        Log.e(TAG, e.getMessage(), e);
                    }

                    @Override
                    public void onNext(TrainLiveResponse trainLiveResponse) {
                        getView().displayLiveDepartures(trainLiveResponse);
                        getView().hideProgress();
                    }
                });
    }

    @Override
    public void requestStations() {
        Observable<List<Station>> observable = mStationsInteractor.fetchStations();
        if(observable == null) {

        }
        observable.subscribe(new Subscriber<List<Station>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().displayError(e.getMessage());
                getView().hideProgress();
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onNext(List<Station> stations) {
                getView().displayStations(stations);
                getView().hideProgress();
            }
        });
    }
}
