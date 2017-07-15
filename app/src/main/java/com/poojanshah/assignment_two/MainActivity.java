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

import com.poojanshah.assignment_two.MVP.IMusicListPresenter;
import com.poojanshah.assignment_two.MVP.IMusicListView;
import com.poojanshah.assignment_two.MVP.MusicListClassicPresenterImple;
import com.poojanshah.assignment_two.MVP.MusicListPopPresenterImple;
import com.poojanshah.assignment_two.MVP.MusicListPresenterImple;
import com.poojanshah.assignment_two.MVP.MusicListRockPresenterImple;
import com.poojanshah.assignment_two.MVP.interactor.InteractorImpl;
import com.poojanshah.assignment_two.model.Music;
import com.poojanshah.assignment_two.model.Result;

public class MainActivity extends AppCompatActivity implements IMusicListView {


    static IMusicListPresenter iMusicListPresenter;
    InteractorImpl interactor_;
    IMusicListView iMusicListView;
    android.support.v4.app.FragmentManager fragmentManager;
    Music music;

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
        this.iMusicListView = this;
        interactor_ = new InteractorImpl();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.classic);
    }

    @Override
    public void onFetchDataSuccess(Music music) {
//        for(Result r: music.getResults()){
//            Log.i("MusicLog", r.getTrackName());
//        }
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
