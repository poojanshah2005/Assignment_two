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


    IMusicListPresenter iMusicListPresenter;
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
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    iMusicListPresenter = new MusicListClassicPresenterImple(interactor_);
                    iMusicListPresenter.attachView(iMusicListView);
                    iMusicListPresenter.performMusicListDisplay();
                    fragmentManager = getSupportFragmentManager();
                    bundle.putSerializable("Music",music);
                    fragmentManager.beginTransaction()
                            .add(R.id.content_main, results)
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    iMusicListPresenter = new MusicListRockPresenterImple(interactor_);
                    iMusicListPresenter.attachView(iMusicListView);
                    iMusicListPresenter.performMusicListDisplay();
                    fragmentManager = getSupportFragmentManager();
                    bundle.putSerializable("Music",music);
                    fragmentManager.beginTransaction()
                            .add(R.id.content_main, results)
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    iMusicListPresenter = new MusicListPopPresenterImple(interactor_);
                    iMusicListPresenter.attachView(iMusicListView);
                    iMusicListPresenter.performMusicListDisplay();
                    fragmentManager = getSupportFragmentManager();
                    bundle.putSerializable("Music",music);
                    fragmentManager.beginTransaction()
                            .add(R.id.content_main, results)
                            .commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.iMusicListView = this;
        interactor_ = new InteractorImpl();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFetchDataSuccess(Music music) {
        for(Result r: music.getResults()){
            Log.i("MusicLog", r.getTrackName());
        }
        this.music = music;
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
}
