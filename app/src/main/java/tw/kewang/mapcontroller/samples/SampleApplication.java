package tw.kewang.mapcontroller.samples;

import android.app.Application;

import tw.kewang.mapcontroller.MapController;

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MapController.initialize(this);
    }
}