package uk.co.donnellyit.travelappjava.ui.neartrain;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.donnellyit.travelappjava.ws.TrainNearResponse;

/**
 * Created by chrisdonnelly on 17/07/2017.
 */

public class FakeTrainNearInteractor implements TrainNearInteractor {
    private final static String FAKE_JSON = "{\\\"minlon\\\":-0.391687,\\\"minlat\\\":51.1062191,\\\"maxlon\\\":0.108313,\\\"maxlat\\\":51.6062191,\\\"searchlon\\\":-0.141687,\\\"searchlat\\\":51.3562191,\\\"page\\\":1,\\\"rpp\\\":25,\\\"total\\\":447,\\\"request_time\\\":\\\"2017-07-13T10:33:24+01:00\\\",\\\"stations\\\":[{\\\"station_code\\\":\\\"WLT\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"WALNGTN\\\",\\\"name\\\":\\\"Wallington\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.150833,\\\"latitude\\\":51.360385,\\\"distance\\\":786},{\\\"station_code\\\":\\\"CSB\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"CRSHLTB\\\",\\\"name\\\":\\\"Carshalton Beeches\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.169797,\\\"latitude\\\":51.357409,\\\"distance\\\":1956},{\\\"station_code\\\":\\\"WDO\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"WADDON\\\",\\\"name\\\":\\\"Waddon\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.117336,\\\"latitude\\\":51.367396,\\\"distance\\\":2098},{\\\"station_code\\\":\\\"CSH\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"CRSHLTN\\\",\\\"name\\\":\\\"Carshalton\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.166369,\\\"latitude\\\":51.368453,\\\"distance\\\":2188},{\\\"station_code\\\":\\\"HCB\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"HKBG\\\",\\\"name\\\":\\\"Hackbridge\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.153908,\\\"latitude\\\":51.37787,\\\"distance\\\":2553},{\\\"station_code\\\":\\\"PUR\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"PURLEY\\\",\\\"name\\\":\\\"Purley\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.114035,\\\"latitude\\\":51.337577,\\\"distance\\\":2826},{\\\"station_code\\\":\\\"RHM\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"REEDHMS\\\",\\\"name\\\":\\\"Reedham (Surrey)\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.123416,\\\"latitude\\\":51.331118,\\\"distance\\\":3066},{\\\"station_code\\\":\\\"PUO\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"PURLEYO\\\",\\\"name\\\":\\\"Purley Oaks\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.098856,\\\"latitude\\\":51.347044,\\\"distance\\\":3145},{\\\"station_code\\\":\\\"SCY\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"SCROYDN\\\",\\\"name\\\":\\\"South Croydon\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.093457,\\\"latitude\\\":51.362963,\\\"distance\\\":3432},{\\\"station_code\\\":\\\"SNR\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"SDSD\\\",\\\"name\\\":\\\"Sanderstead\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.093678,\\\"latitude\\\":51.348282,\\\"distance\\\":3449},{\\\"station_code\\\":\\\"SUO\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"SUTTON\\\",\\\"name\\\":\\\"Sutton (Surrey)\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.191215,\\\"latitude\\\":51.359531,\\\"distance\\\":3459},{\\\"station_code\\\":\\\"WCY\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"WCROYDN\\\",\\\"name\\\":\\\"West Croydon\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.102586,\\\"latitude\\\":51.378426,\\\"distance\\\":3670},{\\\"station_code\\\":\\\"CDN\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"COLSTWN\\\",\\\"name\\\":\\\"Coulsdon Town\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.134464,\\\"latitude\\\":51.322041,\\\"distance\\\":3833},{\\\"station_code\\\":\\\"RDD\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"RDLSDWN\\\",\\\"name\\\":\\\"Riddlesdown\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.099387,\\\"latitude\\\":51.332484,\\\"distance\\\":3949},{\\\"station_code\\\":\\\"ECR\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"ECROYDN\\\",\\\"name\\\":\\\"East Croydon\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.09278,\\\"latitude\\\":51.375452,\\\"distance\\\":4013},{\\\"station_code\\\":\\\"BLM\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"BELM\\\",\\\"name\\\":\\\"Belmont\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.198855,\\\"latitude\\\":51.343813,\\\"distance\\\":4203},{\\\"station_code\\\":\\\"WME\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"WDMNSTR\\\",\\\"name\\\":\\\"Woodmansterne\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.154262,\\\"latitude\\\":51.319018,\\\"distance\\\":4228},{\\\"station_code\\\":\\\"MIJ\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"MITCHMJ\\\",\\\"name\\\":\\\"Mitcham Junction\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.157757,\\\"latitude\\\":51.392948,\\\"distance\\\":4234},{\\\"station_code\\\":\\\"SUC\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"SUTTONC\\\",\\\"name\\\":\\\"Sutton Common\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.196343,\\\"latitude\\\":51.374889,\\\"distance\\\":4325},{\\\"station_code\\\":\\\"CDS\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"COLSDNS\\\",\\\"name\\\":\\\"Coulsdon South\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.137887,\\\"latitude\\\":51.315836,\\\"distance\\\":4498},{\\\"station_code\\\":\\\"KLY\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"KNLY\\\",\\\"name\\\":\\\"Kenley\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.100925,\\\"latitude\\\":51.324775,\\\"distance\\\":4499},{\\\"station_code\\\":\\\"WSU\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"WSUTTON\\\",\\\"name\\\":\\\"West Sutton\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.205173,\\\"latitude\\\":51.365852,\\\"distance\\\":4536},{\\\"station_code\\\":\\\"CHE\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"CHEAM\\\",\\\"name\\\":\\\"Cheam\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.214168,\\\"latitude\\\":51.355477,\\\"distance\\\":5034},{\\\"station_code\\\":\\\"SRS\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"SELHRST\\\",\\\"name\\\":\\\"Selhurst\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.0883,\\\"latitude\\\":51.391926,\\\"distance\\\":5431},{\\\"station_code\\\":\\\"SIH\\\",\\\"atcocode\\\":null,\\\"tiploc_code\\\":\\\"SHLIER\\\",\\\"name\\\":\\\"St Helier (London)\\\",\\\"mode\\\":\\\"train\\\",\\\"longitude\\\":-0.198771,\\\"latitude\\\":51.389899,\\\"distance\\\":5452}]}\n";

    private Context mContext;

    public FakeTrainNearInteractor(Context context) {
        mContext = context;
    }

    @Override
    public Observable<TrainNearResponse> getResponse(double latitude, double longitude, int page, int rpp, String appId, String apiKey) {
        try {
            return Observable.just(buildFakeResponse())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        } catch (IOException e) {
            Log.e(FakeTrainNearInteractor.class.getSimpleName(), e.getMessage(), e);
            return Observable.error(e);
        }
    }

    private TrainNearResponse buildFakeResponse() throws IOException {
        InputStream input = mContext.getAssets().open("near_station_response.json");

        int size = input.available();
        byte[] buffer = new byte[size];
        input.read(buffer);
        input.close();

        // byte buffer into a string
        String fakeJson = new String(buffer);


        Gson gson = new Gson();
        return gson.fromJson(fakeJson, TrainNearResponse.class);
    }
}
