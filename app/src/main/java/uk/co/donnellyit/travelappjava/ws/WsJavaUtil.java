package uk.co.donnellyit.travelappjava.ws;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chrisdonnelly on 30/06/2017.
 */

public class WsJavaUtil {
    public static TransportApiService getService() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkReplayInterceptor okReplayInterceptor = new OkReplayInterceptor();
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(logInterceptor)
                //.addInterceptor(okReplayInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl("https://transportapi.com/v3/")
                .build();

        return retrofit.create(TransportApiService.class);
    }
}
