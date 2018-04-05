package tw.kewang.mapcontroller.samples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.MapControllerReady;

public class WhenInfoWindowClick extends Activity implements MapControllerReady {
    private MapView mv;
    private MapController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.when_info_window_click);

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

        controller.moveToMyLocation();

        controller.whenMapClick((map, latLng) -> {
            MarkerOptions opts = new MarkerOptions();

            opts.position(latLng);
            opts.icon(BitmapDescriptorFactory.defaultMarker());
            opts.title("Test Title");
            opts.snippet("Summary");

            mc.addMarker(opts);
        });

        controller.whenInfoWindowClick((map, marker) -> {
            Toast.makeText(this, marker.getId() + ": " + marker.getTitle(), Toast.LENGTH_SHORT).show();

            marker.hideInfoWindow();
        });
    }
}