package com.poojanshah.assignment_two.Realm;

import com.poojanshah.assignment_two.model.Result;

import java.util.ArrayList;

import io.realm.Realm;
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
                realm.copyToRealm(result);
            }
        });
    }

//    public ArrayList<Person> getCustomers(){
//        ArrayList<Person> people = new ArrayList<>();
//        RealmResults<Person> c = realm.where(Person.class).findAll();
//        for(Person customerModel: c){
//            people.add(customerModel);
//        }
//        return people;
//    }
}