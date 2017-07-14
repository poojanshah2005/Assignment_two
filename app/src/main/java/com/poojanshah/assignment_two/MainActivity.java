package com.poojanshah.assignment_two;

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

    private TextView mTextMessage;

    IMusicListPresenter iMusicListPresenter;
    InteractorImpl interactor_;
    IMusicListView iMusicListView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    iMusicListPresenter = new MusicListClassicPresenterImple(interactor_);
                    iMusicListPresenter.attachView(iMusicListView);
                    iMusicListPresenter.performMusicListDisplay();
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    iMusicListPresenter = new MusicListRockPresenterImple(interactor_);
                    iMusicListPresenter.attachView(iMusicListView);
                    iMusicListPresenter.performMusicListDisplay();
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    iMusicListPresenter = new MusicListPopPresenterImple(interactor_);
                    iMusicListPresenter.attachView(iMusicListView);
                    iMusicListPresenter.performMusicListDisplay();
                    mTextMessage.setText(R.string.title_notifications);
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
//        iMusicListPresenter = new MusicListClassicPresenterImple(interactor_);
//        iMusicListPresenter.attachView(this);
//        iMusicListPresenter.performMusicListDisplay();
//        iMusicListPresenter = new MusicListRockPresenterImple(interactor_);
//        iMusicListPresenter.attachView(this);
//        iMusicListPresenter.performMusicListDisplay();
//        iMusicListPresenter = new MusicListPopPresenterImple(interactor_);
//        iMusicListPresenter.attachView(this);
//        iMusicListPresenter.performMusicListDisplay();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFetchDataSuccess(Music music) {
        for(Result r: music.getResults()){
            Log.i("MusicLog", r.getTrackName());
        }
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
