package com.poojanshah.assignment_two.MVP;

import android.util.Log;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.poojanshah.assignment_two.MVP.interactor.InteractorImpl;
import com.poojanshah.assignment_two.model.Music;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shahp on 14/07/2017.
 */

public class MusicListRockPresenterImple implements IMusicListPresenter {
    InteractorImpl interactor_;
    IMusicListView iMusicListView;

//    public MusicListPresenterImple(IMusicListView iMusicListView) {
//        this.iMusicListView = iMusicListView;
//    }


    public MusicListRockPresenterImple(InteractorImpl interactor_) {
        this.interactor_ = interactor_;
    }

    @Override
    public void attachView(IMusicListView MVPView) {
        this.iMusicListView = MVPView;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void performMusicListDisplay() {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) {
                        // do something with isConnectedToInternet value
                        if (isConnectedToInternet) {
                            interactor_.getMusicRockList()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.newThread())
                                    .subscribe(this::onSuccess, this::OnError);
//                            Toast.makeText(MainActivity.this,"Network is Available",Toast.LENGTH_LONG).show();
                        } else {
//                            Toast.makeText(MainActivity.this,"Network is Available",Toast.LENGTH_LONG).show();
                        }
                    }

                    private void onSuccess(Music music) {
                        iMusicListView.onFetchDataSuccess(music);
                    }

                    private void OnError(Throwable throwable) {
                        Log.i("throwable.getMessage()", throwable.getMessage());
                        Log.i("throwable.getCause()", String.valueOf(throwable.getCause()));
                    }

                });
    }
}
