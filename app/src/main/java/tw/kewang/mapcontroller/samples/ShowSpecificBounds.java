package tw.kewang.mapcontroller.samples;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.ChangeMyLocation;
import tw.kewang.mapcontroller.MapController.MapControllerReady;

public class ShowSpecificBounds extends Activity implements MapControllerReady {
    private MapView mv;
    private LatLng latLng1 = new LatLng(22.03338, 121.56463);
    private LatLng latLng2 = new LatLng(23.03338, 120.56463);
    private MapController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_specific_bounds);

        findView();
        setView(savedInstanceState);
        setListener();
        doExtra();
    }

    private void findView() {
        mv = (MapView) findViewById(R.id.map);
    }

    private void setView(Bundle savedInstanceState) {
        mv.onCreate(savedInstanceState);

        mc = new MapController(mv, this);
    }

    private void setListener() {
    }

    private void doExtra() {
    }

    @Override
    protected void onResume() {
        super.onResume();

        mv.onResume();
    }

    @Override
    protected void onPause() {
        mv.onPause();

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mv.onDestroy();

        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        mv.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mv.onSaveInstanceState(outState);
    }

    @Override
    public void already(MapController controller) {
        mc = controller;

        controller.moveToMyLocation(new ChangeMyLocation() {
            @Override
            public void changed(GoogleMap map, Location location) {
                mc.setBounds(latLng1, latLng2, 10);
            }
        });
    }
}