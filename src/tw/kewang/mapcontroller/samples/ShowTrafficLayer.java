package tw.kewang.mapcontroller.samples;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.MapView;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.MapControllerReady;

public class ShowTrafficLayer extends Activity implements MapControllerReady {
    private MapView mv;
    private MapController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_traffic_layer);

        findView();
        setView(savedInstanceState);
    }

    private void findView() {
        mv = (MapView) findViewById(R.id.map);
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
        controller.moveToMyLocation();

        controller.showTraffic(true);
    }
}