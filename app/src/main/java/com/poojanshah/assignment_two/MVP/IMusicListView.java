package com.poojanshah.assignment_two.MVP;

import com.poojanshah.assignment_two.model.Music;

/**
 * Created by shahp on 14/07/2017.
 */

/**
 * Contract for the View
 */
public interface IMusicListView extends MVPView {

    //mvp step 3

    void onFetchDataSuccess(Music music);
    void onFetchDataFailure(Throwable throwable);
    void onFetchDataCompleted();
    void onFetchDataInProgress();

}