package com.poojanshah.assignment_two.MVP.interactor;

import com.poojanshah.assignment_two.model.Music;

import io.reactivex.Observable;

/**
 * Created by shahp on 14/07/2017.
 */

public interface Interactor {

    Observable<Music> getMusicList();

}
