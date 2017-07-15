package com.poojanshah.assignment_two;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Base64;

import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Poojan on 15/07/2017.
 */

/**
 * for Realm init
 */
public class MyApp extends Application {
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String KEY = "KEY";
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());
        byte[] key;
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        /**
         * for storing and getting Realm Key, with exposing it to the outside world.
         */
        if(!settings.contains(KEY)){
            /**
             * setting Realm key
             */
            key = new byte[64];
            new SecureRandom().nextBytes(key);
            String saveThis = Base64.encodeToString(key, Base64.DEFAULT);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(KEY,saveThis);
            editor.commit();
        } else{
            /**
             * getting Realm key
             */
            String stringFromSharedPrefs = settings.getString(KEY,"");
            key = Base64.decode(stringFromSharedPrefs, Base64.DEFAULT);
        }


        /**
         * RealmConfiguration
         */
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .encryptionKey(key)
                .deleteRealmIfMigrationNeeded()
                .build();
        // Commit the edits!

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

