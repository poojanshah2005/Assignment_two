package com.poojanshah.assignment_two;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.poojanshah.assignment_two.Fragment.Results;
import com.poojanshah.assignment_two.MVP.IMusicListPresenter;
import com.poojanshah.assignment_two.MVP.IMusicListView;
import com.poojanshah.assignment_two.MVP.MusicListClassicPresenterImple;
import com.poojanshah.assignment_two.MVP.MusicListPopPresenterImple;
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


    /**
     * contract for presenetation of api data
     */
    static IMusicListPresenter iMusicListPresenter;
    InteractorImpl interactor_;
    IMusicListView iMusicListView;
    android.support.v4.app.FragmentManager fragmentManager;
    /**
     * Realim init
     */
    Realm realm;
    RealmHelper realmHelper;

    /**
     * Saving types of api list/values
     */
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    String type;

    /**
     * is user  logged in
     */
    private boolean isLoggedIn;

    /**
     * Naviagation bar
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        /**
         * for selecting menu item
         * @param item
         * @return
         */
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


        /**
         * for displaying result form api
         * @param musicType
         */
        private void displayResults(String musicType) {
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
                                Log.i("ClassTracdk","onFetchDataFailure");
                                Bundle args = new Bundle();
                                Music musicNew = new Music();
                                ArrayList<Result> list = realmHelper.getCustomers(musicType);
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
                        }
                    });
        }

        /**
         * Display items for when Genre is Rock
         */
        private void topRock() {
            setType(getString(R.string.rock));
            iMusicListPresenter = new MusicListRockPresenterImple(interactor_);
            displayResults(getType());
        }

        /**
         * Display items for when Genre is Pop
         */
        private void topPop() {
            setType(getString(R.string.pop));
            iMusicListPresenter = new MusicListPopPresenterImple(interactor_);
            displayResults(getType());
        }

        /**
         * Display items for when Genre is Classic
         */
        private void topClassic() {
            setType(getString(R.string.classic));
            iMusicListPresenter = new MusicListClassicPresenterImple(interactor_);
            displayResults(getType());
        }

    };

    /**
     * for swipe to update
     */
    public static void update() {
        iMusicListPresenter.performMusicListDisplay();
    }

    @Override
    public void onBackPressed() {
    }

    /**
     * for the creationg of the main Activity screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setType(getString(R.string.classic));
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        this.iMusicListView = this;
        interactor_ = new InteractorImpl();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.classic);
    }

    /**
     * fetch data based on the the type music genre
     * @param music
     */
    @Override
    public void onFetchDataSuccess(Music music) {
        Log.i("ClassTracdk","onFetchDataSuccess");
        for(Result r: music.getResults()){
            Log.i("MusicLog", r.getTrackName());
            r.setGenre(getType());
            realmHelper.saveData(r);
        }
        Bundle args = new Bundle();
        args.putParcelable("doctor_id", music);
        Results results = new Results ();
        results.setArguments(args);
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, results)
                .addToBackStack(results.getClass().getName())
                .commit();
    }

    /**
     * loading data from realm when there is no internet connection.
     * @param throwable
     */
    @Override
    public void onFetchDataFailure(Throwable throwable) {
        Log.i("ClassTrack","onFetchDataFailure");
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
        Log.i("ClassTrack","onFetchDataCompleted");

    }

    @Override
    public void onFetchDataInProgress() {
        Log.i("ClassTrack","onFetchDataInProgress");

    }

    /**
     * Closing Realm when the application is close, to prevent memory leaks
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
