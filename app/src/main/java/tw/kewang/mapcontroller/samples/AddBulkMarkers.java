package tw.kewang.mapcontroller.samples;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.MapControllerReady;

public class AddBulkMarkers extends Activity implements MapControllerReady {
    private MapView mv;
    private ArrayList<MarkerOptions> allOpts;
    private MapController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_bulk_markers);

        findView();
        setView(savedInstanceState);
    }

    private void findView() {
        mv = findViewById(R.id.map);
    }

    private void setView(Bundle savedInstanceState) {
        mv.onCreate(savedInstanceState);

        mc = new MapController(mv, this);
    }

    private void addMarker(double lat, double lng, String name) {
        MarkerOptions opts = new MarkerOptions();

        opts.position(new LatLng(lat, lng));
        opts.icon(BitmapDescriptorFactory.defaultMarker());
        opts.title(name);
        opts.snippet("Summary");

        allOpts.add(opts);
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
        controller.moveToMyLocation();

        allOpts = new ArrayList<>();

        addMarker(25.04157, 121.51519, "228");
        addMarker(25.03338, 121.56226, "Taipei 101");
        addMarker(24.99836, 121.58360, "Taipei Zoo");

        controller.addMarkers(allOpts);
    }
}