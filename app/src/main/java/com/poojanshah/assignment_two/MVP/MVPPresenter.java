package com.poojanshah.assignment_two.MVP;

/**
 * Created by shahp on 14/07/2017.
 */

/**
 * Contract for the Presenter
 * @param <v>
 */
public interface MVPPresenter <v extends MVPView> {

    void attachView(v MVPView);

    void detachView();

}