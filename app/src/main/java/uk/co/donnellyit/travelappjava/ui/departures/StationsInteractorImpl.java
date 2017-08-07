package uk.co.donnellyit.travelappjava.ui.departures;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.donnellyit.travelappjava.ws.Station;

/**
 * Created by chrisdonnelly on 27/07/2017.
 */

public class StationsInteractorImpl implements StationsInteractor {

    private final static String LOGTAG = StationsInteractorImpl.class.getSimpleName();
    private Context mContext;

    public StationsInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public Observable<List<Station>> fetchStations() {
        Observable<List<Station>> observable;

        try {
            observable = Observable.just(loadStations());
        } catch (IOException e) {
            Log.e(LOGTAG, e.getMessage(), e);
            return null;
        }

        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<Station> loadStations() throws IOException {
        CSVReader csvReader = new CSVReader(new InputStreamReader(mContext.getAssets().open("station_codes.csv")));
        // Skip the first row of the csv file
        csvReader.readNext();
        List<Station> stations = new ArrayList<>();
        while(true) {
            String[] row = csvReader.readNext();
            if(row != null) {
                Station station = new Station(row[0], row[1]);
                stations.add(station);
            } else {
                break;
            }
        }

        return stations;
    }
}
