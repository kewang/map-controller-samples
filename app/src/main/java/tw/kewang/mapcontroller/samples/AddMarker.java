package tw.kewang.mapcontroller.samples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.MapControllerReady;

public class AddMarker extends Activity implements MapControllerReady {
    private MapView mv;
    private MapController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_marker);

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

    private void addMarker(MarkerOptions opts) {
        mc.addMarker(opts, (map, marker) -> Toast.makeText(this, marker.getId(), Toast.LENGTH_SHORT).show());
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

        controller.whenMapClick((map, latLng) -> {
            MarkerOptions opts = new MarkerOptions();

            opts.position(latLng);
            opts.icon(BitmapDescriptorFactory.defaultMarker());
            opts.title("Test Title");
            opts.snippet("Summary");

            addMarker(opts);
        });
    }
}