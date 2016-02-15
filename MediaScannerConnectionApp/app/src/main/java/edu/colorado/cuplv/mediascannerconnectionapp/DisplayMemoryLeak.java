package edu.colorado.cuplv.mediascannerconnectionapp;

import android.app.Application;

import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class DisplayMemoryLeak extends Application {

    private static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this, DisplayLeakService.class, new ExcludedRefs.Builder().build());
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }
}
