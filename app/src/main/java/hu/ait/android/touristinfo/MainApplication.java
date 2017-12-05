package hu.ait.android.touristinfo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by oliviakim on 12/4/17.
 */

public class MainApplication extends Application {

    private Realm realmSights;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }

    public void openRealm() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realmSights = Realm.getInstance(config);
    }

    public void closeRealm() {
        realmSights.close();
    }

    public Realm getRealmSights() {
        return realmSights;
    }

}
