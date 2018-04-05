package tw.kewang.mapcontroller.samples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.MapControllerReady;

public class ShowSpecificLocation extends Activity implements MapControllerReady {
    private MapView mv;
    private LatLng latLng = new LatLng(25.03338, 121.56463);
    private Button btnMove;
    private Button btnAnimate;
    private MapController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_specific_location);

        findView();
        setView(savedInstanceState);
        setListener();
    }

    private void findView() {
        mv = findViewById(R.id.map);
        btnMove = findViewById(R.id.button_move);
        btnAnimate = findViewById(R.id.button_animate);
    }

    private void setView(Bundle savedInstanceState) {
        mv.onCreate(savedInstanceState);

        mc = new MapController(mv, this);
    }

    private void setListener() {
        btnMove.setOnClickListener(v -> mc.moveTo(latLng));

        btnAnimate.setOnClickListener(v -> mc.animateTo(latLng, (map, position) -> Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()));
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
    }
}