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
/**
 * Implementation of Presenter Contract for Pop Music
 */
public class MusicListPopPresenterImple implements IMusicListPresenter {
    InteractorImpl interactor_;
    IMusicListView iMusicListView;

    public MusicListPopPresenterImple(InteractorImpl interactor_) {
        this.interactor_ = interactor_;
    }

    @Override
    public void attachView(IMusicListView MVPView) {
        this.iMusicListView = MVPView;
    }

    @Override
    public void detachView() {

    }

    /**
     * for displaying results from API, and passing them on the correct view contact
     */
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
                            interactor_.getMusicPopList()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.newThread())
                                    .subscribe(this::onSuccess, this::OnError);
//                            Toast.makeText(MainActivity.this,"Network is Available",Toast.LENGTH_LONG).show();
                        } else {
//                            Toast.makeText(MainActivity.this,"Network is Available",Toast.LENGTH_LONG).show();
                        }
                    }

                    /**
                     * passing Music objects to correct View contract
                     * @param music
                     */
                    private void onSuccess(Music music) {
                        iMusicListView.onFetchDataSuccess(music);
                    }

                    /**
                     * for when and error is thrown by api
                     * @param throwable
                     */
                    private void OnError(Throwable throwable) {
                        Log.i("throwable.getMessage()", throwable.getMessage());
                        Log.i("throwable.getCause()", String.valueOf(throwable.getCause()));
                    }

                });
    }
}
