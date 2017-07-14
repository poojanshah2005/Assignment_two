package com.poojanshah.assignment_two.MVP.interactor;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.poojanshah.assignment_two.RequestInterface;
import com.poojanshah.assignment_two.model.Music;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.poojanshah.assignment_two.Consts.BASE_URL;

/**
 * Created by shahp on 14/07/2017.
 */

public class InteractorImpl implements Interactor {
    static Retrofit retrofit;
    static OkHttpClient okHttpClient;
    RequestInterface requestInterface;

    public InteractorImpl() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        File outputDir = context.getCacheDir();
//        Cache cache = new Cache(outputDir, 50000);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        requestInterface = retrofit.create(RequestInterface.class);
//        return retrofit.create(RequestInterface.class);
    }

    @Override
    public Observable<Music> getMusicRockList() {
        return requestInterface.getMusicRockList();
    }

    @Override
    public Observable<Music> getMusicClassicList() {
        return requestInterface.getMusicClassicList();
    }

    @Override
    public Observable<Music> getMusicPopList() {
        return requestInterface.getMusicPopList();
    }
}
