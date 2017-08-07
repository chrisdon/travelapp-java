package uk.co.donnellyit.travelappjava.ui.neartrain;

import android.content.Context;
import android.location.Location;

import rx.Subscriber;
import uk.co.donnellyit.travelappjava.R;
import uk.co.donnellyit.travelappjava.ui.common.TAPresenter;
import uk.co.donnellyit.travelappjava.util.EspressoIdlingResource;
import uk.co.donnellyit.travelappjava.ws.TrainNearResponse;

/**
 * Created by chrisdonnelly on 30/06/2017.
 */

public class NearTrainPresenter extends TAPresenter<NearTrainContract.View> implements
        NearTrainContract.Presenter, NearTrainListener
{

    private Context mContext;
    private LocationInteractor mLocationInteractor;
    private TrainNearInteractor mTrainNearInteractor;
    private int mPageNumber;

    public NearTrainPresenter(Context context, LocationInteractor locationInteractor,
                              TrainNearInteractor trainNearInteractor) {
        mContext = context;
        mLocationInteractor = locationInteractor;
        mTrainNearInteractor = trainNearInteractor;
    }

    @Override
    public void getNearStations(int pageNumber) {
        mPageNumber = pageNumber;
        getView().showProgress();
        EspressoIdlingResource.increment(); // App is busy until further notice
        mLocationInteractor.getLocation(mContext, this);
    }

    @Override
    public void onError() {
        EspressoIdlingResource.decrement(); // Set app as idle.
        getView().hideProgress();
        getView().onError(mContext.getString(R.string.location_error));
    }

    @Override
    public void onUpdate(Location oldLoc, long oldTime, Location newLoc, long newTime) {
        String api_key  = mContext.getString(R.string.api_key);
        String app_id = mContext.getString(R.string.app_id);
        mTrainNearInteractor.getResponse(newLoc.getLatitude(), newLoc.getLongitude(), mPageNumber, 25,
                app_id, api_key).subscribe(new Subscriber<TrainNearResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                EspressoIdlingResource.decrement(); // Set app as idle.
                getView().hideProgress();
                getView().onError(mContext.getString(R.string.server_error));
            }

            @Override
            public void onNext(TrainNearResponse trainNearResponse) {
                EspressoIdlingResource.decrement(); // Set app as idle.
                getView().displayNearestStations(trainNearResponse);
                getView().hideProgress();
            }
        });
    }
}
