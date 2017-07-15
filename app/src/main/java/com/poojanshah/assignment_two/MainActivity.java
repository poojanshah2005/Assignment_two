package com.poojanshah.assignment_two;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.poojanshah.assignment_two.MVP.IMusicListPresenter;
import com.poojanshah.assignment_two.MVP.IMusicListView;
import com.poojanshah.assignment_two.MVP.MusicListClassicPresenterImple;
import com.poojanshah.assignment_two.MVP.MusicListPopPresenterImple;
import com.poojanshah.assignment_two.MVP.MusicListPresenterImple;
import com.poojanshah.assignment_two.MVP.MusicListRockPresenterImple;
import com.poojanshah.assignment_two.MVP.interactor.InteractorImpl;
import com.poojanshah.assignment_two.Realm.RealmHelper;
import com.poojanshah.assignment_two.model.Music;
import com.poojanshah.assignment_two.model.Result;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements IMusicListView {


    static IMusicListPresenter iMusicListPresenter;
    InteractorImpl interactor_;
    IMusicListView iMusicListView;
    android.support.v4.app.FragmentManager fragmentManager;
    Music music;
    Realm realm;
    RealmHelper realmHelper;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Results results = new Results();
            Bundle bundle = new Bundle();
            fragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.classic:
                    topClassic();
                    return true;
                case R.id.rock:
                    topRock();
                    return true;
                case R.id.pop:
                    topPop();
                    return true;
            }
            return false;
        }

        private void topClassic() {
            iMusicListPresenter = new MusicListClassicPresenterImple(interactor_);
            displayResults();
        }

        private void displayResults() {
            iMusicListPresenter.attachView(iMusicListView);
            iMusicListPresenter.performMusicListDisplay();

            ReactiveNetwork.observeInternetConnectivity()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Boolean>() {
                        @Override public void accept(Boolean isConnectedToInternet) {
                            // do something with isConnectedToInternet value
                            if(isConnectedToInternet){
                                Toast.makeText(MainActivity.this,"Network is Available",Toast.LENGTH_LONG).show();
                            } else{
                                Toast.makeText(MainActivity.this,"Network is Not Available",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

        private void topRock() {
            iMusicListPresenter = new MusicListRockPresenterImple(interactor_);
            displayResults();
        }

        private void topPop() {
            iMusicListPresenter = new MusicListPopPresenterImple(interactor_);
            displayResults();
        }

    };

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        this.iMusicListView = this;
        interactor_ = new InteractorImpl();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.classic);
    }

    @Override
    public void onFetchDataSuccess(Music music) {
        for(Result r: music.getResults()){
            Log.i("MusicLog", r.getTrackName());
            realmHelper.saveData(r);
        }
        Bundle args = new Bundle();
        args.putParcelable("doctor_id",music);
        Results results = new Results ();
        results.setArguments(args);
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, results)
                .addToBackStack(results.getClass().getName())
                .commit();
    }

    @Override
    public void onFetchDataFailure(Throwable throwable) {
        Bundle args = new Bundle();
        Music musicNew = new Music();
        ArrayList<Result> list = realmHelper.getCustomers(getString(R.string.rock));
        musicNew.setResults(list);
        musicNew.setResultCount(list.size());
        args.putParcelable("doctor_id",musicNew);
        Results results = new Results ();
        results.setArguments(args);
        fragmentManager.beginTransaction()
                .replace(R.id.content_main,results)
                .addToBackStack(results.getClass().getName())
                .commit();
    }

    @Override
    public void onFetchDataCompleted() {

    }

    @Override
    public void onFetchDataInProgress() {

    }

    public static void update() {
        iMusicListPresenter.performMusicListDisplay();
    }
}
