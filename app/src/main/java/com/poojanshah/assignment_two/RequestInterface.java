package com.poojanshah.assignment_two;

import com.poojanshah.assignment_two.model.Music;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by shahp on 14/07/2017.
 */

public interface RequestInterface {
    @GET(Consts.ROCK_MUSIC)
    Observable<Music> getMusicList();
}
