package uk.co.donnellyit.travelappjava.ws;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chrisdonnelly on 25/07/2017.
 */

public interface TransportApiService {
    @GET("uk/train/station/{station_code}/live.json")
    Observable<TrainLiveResponse> getLiveDepartures(@Path("station_code") String station_code,
                          @Query("app_id") String app_id,
                          @Query("app_key") String app_key);
    @GET("uk/train/stations/near.json")
    Observable<TrainNearResponse> getNearDepartures(@Query("lat") double lat,
                                                    @Query("lon") double lon,
                                                    @Query("page") int page,
                                                    @Query("rpp") int rpp,
                                                    @Query("app_id") String app_id,
                                                    @Query("app_key") String app_key);
}
