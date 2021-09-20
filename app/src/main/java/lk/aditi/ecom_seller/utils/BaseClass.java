package lk.aditi.ecom_seller.utils;

import android.app.Application;

public class BaseClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

       SharedPrefsManager.initialize(this);
    }
}
