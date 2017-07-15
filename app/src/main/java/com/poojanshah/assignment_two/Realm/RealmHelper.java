package com.poojanshah.assignment_two.Realm;

import com.poojanshah.assignment_two.model.Result;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Poojan on 15/07/2017.
 */

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void saveData(final Result result){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(result);
            }

        });
    }

    public ArrayList<Result> getCustomers(String type){
        ArrayList<Result> output;
        RealmResults<Result> result = realm.where(Result.class).equalTo("primaryGenreName",type).findAll();
        output = new ArrayList<>(result);
        return output;
    }
}
